#include <CL/sycl.hpp>
#include <array>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

#define WORKGROUP_SIZE 8
#define EPSILON 0.1

const int heightA = 8, widthA = 9, heightB = 9, widthB = 8;
// const int heightA = 47, widthA = 513, heightB = 513, widthB = 81;
const int Asize = heightA * widthA;
const int Bsize = heightB * widthB;
const int resultSize = heightA * widthB;

float getRandom() { return ((rand() / (float)RAND_MAX) * 10); }
void cpuMultiply(std::array<float, resultSize> &result,
                 const std::array<float, Asize> &A,
                 const std::array<float, Bsize> &B);
void gpuMultiply(std::array<float, resultSize> &result,
                 const std::array<float, Asize> &A,
                 const std::array<float, Bsize> &B);
int ceilDivide(int a, int b) { return (a + b - 1) / b; }
bool areSame(float a, float b) { return fabs(a - b) < EPSILON; }

int main() {
  std::array<float, resultSize> resultCPU;
  std::array<float, resultSize> resultGPU;
  std::array<float, Asize> A;
  std::array<float, Bsize> B;

  srand((int)time(NULL));
  for (int i = 0; i < heightA; i++)
    for (int j = 0; j < widthA; j++)
      A[i * widthA + j] = getRandom();
  for (int i = 0; i < heightB; i++)
    for (int j = 0; j < widthB; j++)
      B[i * widthB + j] = getRandom();

  cpuMultiply(resultCPU, A, B);
  gpuMultiply(resultGPU, A, B);

  std::cout << "\n\n\n\n"
            << "A\n";
  for (int i = 0; i < heightA; i++) {
    for (int j = 0; j < widthA; j++)
      std::cout << A[i * widthA + j] << ' ';
    printf("\n");
  }
  std::cout << "\n\n\n\n"
            << "B\n";
  for (int i = 0; i < heightB; i++) {
    for (int j = 0; j < widthB; j++)
      std::cout << B[i * widthB + j] << ' ';
    printf("\n");
  }
  std::cout << "\n\n\n\n"
            << "Result CPU\n";
  for (int i = 0; i < heightA; i++) {
    for (int j = 0; j < widthB; j++)
      std::cout << resultCPU[i * widthB + j] << ' ';
    printf("\n");
  }
  // std::cout << "\n\n\n\n"
  //           << "Result GPU\n";
  // for (int i = 0; i < heightA; i++) {
  //   for (int j = 0; j < widthB; j++)
  //     std::cout << resultGPU[i * widthB + j] << ' ';
  //   printf("\n");
  // }

  std::cout << "Errors:\n";

  for (int i = 0; i < heightA; i++) {
    for (int j = 0; j < widthB; j++)
      if (areSame(resultCPU[i * widthB + j], resultGPU[i * widthB + j]) ==
          false)
        printf("Error! %d %d: %f %f\n", i, j, resultCPU[i * widthB + j],
               resultGPU[i * widthB + j]);
  }

  std::cout << "Finished!\n";

  return 0;
}

void gpuMultiply(std::array<float, resultSize> &result,
                 const std::array<float, Asize> &A,
                 const std::array<float, Bsize> &B) {
  using namespace cl::sycl;

  default_selector device_selector;
  queue device_queue(device_selector);

  std::cout << "Device: "
            << device_queue.get_device().get_info<info::device::name>()
            << std::endl;

  int groupCount = ceilDivide(heightB, WORKGROUP_SIZE);
  int bufferWidth = widthB * groupCount;

  range<1> a_size{Asize};
  range<1> b_size{Bsize};
  range<1> buffer_size{(unsigned long)(resultSize * groupCount)};
  range<1> result_size{resultSize};

  buffer<float, 1> a_matrix(A.data(), a_size);
  buffer<float, 1> b_matrix(B.data(), b_size);
  buffer<float, 1> buffer_matrix(buffer_size);
  buffer<float, 1> result_matrix(result.data(), result_size);

  range<2> localSize(WORKGROUP_SIZE, WORKGROUP_SIZE);
  // range<2> globalSize(WORKGROUP_SIZE, WORKGROUP_SIZE);
  range<2> globalSize(WORKGROUP_SIZE, WORKGROUP_SIZE * groupCount);

  device_queue.submit([&](handler &cgh) {
    // matrix A accessor
    auto a_in = a_matrix.get_access<access::mode::read>(cgh);
    // matrix B accessor
    auto b_in = b_matrix.get_access<access::mode::read>(cgh);
    // matrix buffer accessor
    auto buf_out = buffer_matrix.get_access<access::mode::write>(cgh);

    // local matrix A accessor
    accessor<float, 2, access::mode::read_write, access::target::local> localA(
        range<2>(WORKGROUP_SIZE, WORKGROUP_SIZE), cgh);
    // local matrix B accessor
    accessor<float, 2, access::mode::read_write, access::target::local> localB(
        range<2>(WORKGROUP_SIZE, WORKGROUP_SIZE), cgh);

    cgh.parallel_for<class MatrixMultiplication>(
        nd_range<2>(globalSize, localSize), [=](nd_item<2> item) {
          id<2> localId = item.get_local_id();
          id<2> globalId = item.get_local_id();

          int groupId = item.get_group(1);
          int widthC = widthB * groupCount;

          // lokalni indeks niti
          int ly = localId[0];
          int lx = localId[1];

          // globalni indeks niti
          int gy = globalId[0];
          int gx = globalId[1];

          int matrixB_y = groupId * WORKGROUP_SIZE + ly;
          int matrixC_start = groupId * widthB;

          // int ceilDivide(int a, int b) { return (a + b - 1) / b; }
          int Aiterations = (heightA + WORKGROUP_SIZE - 1) / WORKGROUP_SIZE;
          int Biterations = (widthB + WORKGROUP_SIZE - 1) / WORKGROUP_SIZE;

          // move vertically over A
          for (int i = 0; i < Aiterations; i++) {
            if (i * WORKGROUP_SIZE + ly < heightA && gx < widthA) {
              localA[ly][lx] = a_in[(i * WORKGROUP_SIZE + ly) * widthA + gx];
            } else {
              localA[ly][lx] = 0;
            }

            for (int j = 0; j < Biterations; j++) {
              if (j * WORKGROUP_SIZE + lx < widthB && matrixB_y < heightB) {
                localB[ly][lx] =
                    b_in[matrixB_y * widthB + (j * WORKGROUP_SIZE + lx)];
              } else {
                localB[ly][lx] = 0;
              }

              item.barrier(access::fence_space::local_space);

              float sum = 0;
              for (int k = 0; k < WORKGROUP_SIZE; k++) {
                sum += localA[ly][k] * localB[k][lx];
              }

              if (i * WORKGROUP_SIZE + ly < heightA &&
                  j * WORKGROUP_SIZE + lx < widthB) {
                buf_out[matrixC_start + (i * WORKGROUP_SIZE + ly) * widthC +
                        (j * WORKGROUP_SIZE + lx)] = sum;
              }

              item.barrier(access::fence_space::local_space);
            }
          }
        });
  });

  device_queue.submit([&](handler &cgh) {
    // matrix B accessor
    auto buf_in = buffer_matrix.get_access<access::mode::read>(cgh);
    // matrix buffer accessor
    auto result_out = result_matrix.get_access<access::mode::write>(cgh);

    cgh.parallel_for<class MatrixAggregate>(
        nd_range<2>(globalSize, localSize), [=](nd_item<2> item) {
          id<2> localId = item.get_local_id();
          id<2> globalId = item.get_local_id();
          int threadCount = item.get_global_range(1);

          // lokalni indeks niti
          int ly = localId[0];
          int lx = localId[1];

          // globalni indeks niti
          int gy = globalId[0];
          int gx = globalId[1];

          // alociramo lokalni pomnilnik
          float sum = 0;

          if (gy < heightA && gx < widthB) {
            for (int i = gx; i < bufferWidth; i += widthB) {
              sum += buf_in[gy * bufferWidth + i];
            }

            result_out[gy * widthB + gx] = sum;
          }
        });
  });

  auto bufacc1 = buffer_matrix.get_access<access::mode::read>();
  for (int i = 0; i < heightA; i++) {
    for (int j = 0; j < bufferWidth; j++) {
      std::cout << bufacc1[i * bufferWidth + j] << ' ';
    }
    std::cout << '\n';
  }
}

void cpuMultiply(std::array<float, resultSize> &result,
                 const std::array<float, Asize> &A,
                 const std::array<float, Bsize> &B) {
  for (int i = 0; i < heightA; i++) {
    for (int j = 0; j < widthB; j++) {
      float sum = 0;

      for (int k = 0 * widthA / 2; k < widthA; k++) {
        sum += A[i * widthA + k] * B[k * widthB + j];
      }

      result[i * widthB + j] = sum;
    }
  }
}
