#include <CL/sycl.hpp>
#include <array>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

#define WORKGROUP_SIZE 8
#define EPSILON 0.1

const int heightA = 47, widthA = 513, heightB = 513, widthB = 81;
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
int roundToNearest(int n, int f) { return n + f - 1 - (n - 1) % f; }

int main() {
  std::cout << "Height A: " << heightA << '\n';
  std::cout << "Width A: " << widthA << '\n';
  std::cout << "Height B: " << heightB << '\n';
  std::cout << "Width B: " << widthB << '\n';

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
  namespace sycl = cl::sycl;

  sycl::default_selector device_selector;
  sycl::queue device_queue(device_selector);

  std::cout << "Device: "
            << device_queue.get_device().get_info<sycl::info::device::name>()
            << std::endl;

  int groupCount = ceilDivide(heightB, WORKGROUP_SIZE);
  int bufferWidth = widthB * groupCount;

  // specificiramo dimenzije matrik
  sycl::range<1> a_size{Asize};
  sycl::range<1> b_size{Bsize};
  sycl::range<1> buffer_size{(unsigned long)(resultSize * groupCount)};
  sycl::range<1> result_size{resultSize};

  // specificiramo dimenzije ščepcev
  sycl::range<2> localSize(WORKGROUP_SIZE, WORKGROUP_SIZE);
  sycl::range<2> multiplyGlobalSize(WORKGROUP_SIZE,
                                    WORKGROUP_SIZE * groupCount);

  // specificiramo bufferje vseh matrik
  sycl::buffer<float, 1> a_matrix(A.data(), a_size);
  sycl::buffer<float, 1> b_matrix(B.data(), b_size);
  sycl::buffer<float, 1> buffer_matrix(buffer_size);
  sycl::buffer<float, 1> result_matrix(result.data(), result_size);

  // poženemo prvi ščepec
  device_queue.submit([&](sycl::handler &cgh) {
    // dostopnik matrike A
    auto a_in = a_matrix.get_access<sycl::access::mode::read>(cgh);
    // dostopnik matrike B
    auto b_in = b_matrix.get_access<sycl::access::mode::read>(cgh);
    // dostopnik začasne zbiralne matrike
    auto buf_out = buffer_matrix.get_access<sycl::access::mode::write>(cgh);

    // dostopnik začasne matrike A v lokalnem pomnilniku GPU
    sycl::accessor<float, 2, sycl::access::mode::read_write,
                   sycl::access::target::local>
        localA(sycl::range<2>(WORKGROUP_SIZE, WORKGROUP_SIZE), cgh);
    // dostopnik začasne matrike B v lokalnem pomnilniku GPU
    sycl::accessor<float, 2, sycl::access::mode::read_write,
                   sycl::access::target::local>
        localB(sycl::range<2>(WORKGROUP_SIZE, WORKGROUP_SIZE), cgh);

    // stream za izpis in razhroščevanje
    sycl::stream out(1024, 256, cgh);

    cgh.parallel_for<class MatrixMultiplication>(
        sycl::nd_range<2>(multiplyGlobalSize, localSize),
        [=](sycl::nd_item<2> item) {
          int groupId = item.get_group(1);
          int widthC = widthB * groupCount;

          // lokalni indeks niti
          int ly = item.get_local_id(0);
          int lx = item.get_local_id(1);

          // globalni indeks niti
          int gy = item.get_global_id(0);
          int gx = item.get_global_id(1);

          int matrixB_y = groupId * WORKGROUP_SIZE + ly;
          int matrixC_start = groupId * widthB;

          int Aiterations = (heightA + WORKGROUP_SIZE - 1) / WORKGROUP_SIZE;
          int Biterations = (widthB + WORKGROUP_SIZE - 1) / WORKGROUP_SIZE;

          // premikamo se po A vertikalno
          for (int i = 0; i < Aiterations; i++) {
            if (i * WORKGROUP_SIZE + ly < heightA && gx < widthA)
              localA[ly][lx] = a_in[(i * WORKGROUP_SIZE + ly) * widthA + gx];
            else
              localA[ly][lx] = 0;

            // premikamo se po B horizontalno
            for (int j = 0; j < Biterations; j++) {
              if (j * WORKGROUP_SIZE + lx < widthB && matrixB_y < heightB)
                localB[ly][lx] =
                    b_in[matrixB_y * widthB + (j * WORKGROUP_SIZE + lx)];
              else
                localB[ly][lx] = 0;

              item.barrier(sycl::access::fence_space::local_space);

              float sum = 0;
              for (int k = 0; k < WORKGROUP_SIZE; k++)
                sum += localA[ly][k] * localB[k][lx];

              if (i * WORKGROUP_SIZE + ly < heightA &&
                  j * WORKGROUP_SIZE + lx < widthB)
                buf_out[matrixC_start + (i * WORKGROUP_SIZE + ly) * widthC +
                        (j * WORKGROUP_SIZE + lx)] = sum;

              item.barrier(sycl::access::fence_space::local_space);
            }
          }
        });
  });

  // specificiramo dimenzije 2. ščepca
  sycl::range<2> agregateGlobalSize(roundToNearest(heightA, WORKGROUP_SIZE),
                                    roundToNearest(widthB, WORKGROUP_SIZE));

  device_queue.submit([&](sycl::handler &cgh) {
    // dostopnik začasne zbiralne matrike
    auto buf_in = buffer_matrix.get_access<sycl::access::mode::read>(cgh);
    // dostopnik končne izhodne matrke
    auto result_out = result_matrix.get_access<sycl::access::mode::write>(cgh);

    cgh.parallel_for<class MatrixAggregate>(
        sycl::nd_range<2>(agregateGlobalSize, localSize),
        [=](sycl::nd_item<2> item) {
          // število niti po širini
          int threadCount = item.get_global_range(1);

          // lokalni indeks niti
          int ly = item.get_local_id(0);
          int lx = item.get_local_id(1);

          // globalni indeks niti
          int gy = item.get_global_id(0);
          int gx = item.get_global_id(1);

          float sum = 0;

          if (gy < heightA && gx < widthB) {
            for (int i = gx; i < bufferWidth; i += widthB)
              sum += buf_in[gy * bufferWidth + i];

            result_out[gy * widthB + gx] = sum;
          }
        });
  });
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
