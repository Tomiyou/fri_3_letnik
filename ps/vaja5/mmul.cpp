#include <CL/cl.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>

#define WORKGROUP_SIZE 8
#define MAX_SOURCE_SIZE 16384
#define EPSILON 0.1

float *normalMultiply(int hA, int wA, int hB, int wB, float *A, float *B);
float *longMultiply(int hA, int wA, int hB, int wB, float *A, float *B);
float *cpuMultiply(int hA, int wA, int hB, int wB, float *A, float *B);

float getRandom() { return ((rand() / (float)RAND_MAX) * 10); }

bool AreSame(float a, float b) { return fabs(a - b) < EPSILON; }

unsigned long long getTime() {
  struct timeval tv;

  gettimeofday(&tv, NULL);

  unsigned long long millisecondsSinceEpoch =
      (unsigned long long)(tv.tv_sec) * 1000 +
      (unsigned long long)(tv.tv_usec) / 1000;

  return millisecondsSinceEpoch;
}

int main() {
  int hA = 9;
  int wA = 8;
  int hB = 8;
  int wB = 9;
  // int hA = 47;
  // int wA = 513;
  // int hB = 513;
  // int wB = 81;

  float *A = (float *)malloc(hA * wA * sizeof(float));
  float *B = (float *)malloc(hB * wB * sizeof(float));

  // Inicializacija matrik
  int i, j;
  srand((int)time(NULL));
  for (i = 0; i < hA; i++)
    for (j = 0; j < wA; j++)
      A[i * wA + j] = getRandom();
  for (i = 0; i < hB; i++)
    for (j = 0; j < wB; j++)
      B[i * wB + j] = getRandom();

  unsigned long long t1 = getTime();
  float *C = cpuMultiply(hA, wA, hB, wB, A, B);
  unsigned long long t2 = getTime();
  printf("Elapsed milis CPU: %llu\n", t2 - t1);
  
  float *D = longMultiply(hA, wA, hB, wB, A, B);
  
  // printf("\n\n\n\n");
  // printf("A\n");
  // for (i = 0; i < hA; i++) {
  //   for (j = 0; j < wA; j++) {
  //     printf("%f, ", A[i * wA + j]);
  //   }
  //   printf("\n");
  // }
  // printf("\n\n\n\n");
  // printf("B\n");
  // for (i = 0; i < hB; i++) {
  //   for (j = 0; j < wB; j++) {
  //     printf("%f, ", B[i * wB + j]);
  //   }
  //   printf("\n");
  // }
  // printf("\n\n\n\n");
  // printf("C\n");
  // for (i = 0; i < hA; i++) {
  //   for (j = 0; j < wB; j++) {
  //     printf("%f, ", C[i * wB + j]);
  //   }
  //   printf("\n");
  // }
  // printf("\n\n\n\n");
  // printf("D\n");
  // for (i = 0; i < hA; i++) {
  //   for (j = 0; j < wB; j++) {
  //     printf("%f, ", D[i * wB + j]);
  //   }
  //   printf("\n");
  // }
  for (i = 0; i < hA; i++) {
    for (j = 0; j < wB; j++)
      if (AreSame(C[i * wB + j], D[i * wB + j]) == false)
        printf("Error! %d %d: %f %f\n", i, j, C[i * wB + j], D[i * wB + j]);
  }

  free(A);
  free(B);
  free(C);
  free(D);
}

float *cpuMultiply(int hA, int wA, int hB, int wB, float *A, float *B) {
  float *C = (float *)malloc(hA * wB * sizeof(float));

  for (int i = 0; i < hA; i++) {
    for (int j = 0; j < wB; j++) {
      float sum = 0;
      
      for (int k = 0 * wA/2; k < wA; k++) {
        sum += A[i * wA + k] * B[k * wB + j];
      }

      C[i * wB + j] = sum;
    }
  }

  return C;
};

float *normalMultiply(int hA, int wA, int hB, int wB, float *A, float *B) {
  char ch;
  cl_int ret;

  // Branje datoteke
  FILE *fp;
  char *source_str;
  size_t source_size;

  fp = fopen("kernel.cl", "r");
  if (!fp) {
    fprintf(stderr, ":-(#\n");
    exit(1);
  }
  source_str = (char *)malloc(MAX_SOURCE_SIZE);
  source_size = fread(source_str, 1, MAX_SOURCE_SIZE, fp);
  source_str[source_size] = '\0';
  fclose(fp);

  // Rezervacija pomnilnika
  float *C = (float *)malloc(hA * wB * sizeof(float));

  // Podatki o platformi
  cl_platform_id platform_id[10];
  cl_uint ret_num_platforms;
  char *buf;
  size_t buf_len;
  ret = clGetPlatformIDs(10, platform_id, &ret_num_platforms);
  // max. število platform, kazalec na platforme, dejansko število platform

  // Podatki o napravi
  cl_device_id device_id[10];
  cl_uint ret_num_devices;
  // Delali bomo s platform_id[0] na GPU
  ret = clGetDeviceIDs(platform_id[0], CL_DEVICE_TYPE_GPU, 10, device_id,
                       &ret_num_devices);
  // izbrana platforma, tip naprave, koliko naprav nas zanima
  // kazalec na naprave, dejansko število naprav

  // Kontekst
  cl_context context =
      clCreateContext(NULL, 1, &device_id[0], NULL, NULL, &ret);
  // kontekst: vključene platforme - NULL je privzeta, število naprav,
  // kazalci na naprave, kazalec na call-back funkcijo v primeru napake
  // dodatni parametri funkcije, številka napake

  // Ukazna vrsta
  cl_command_queue command_queue =
      clCreateCommandQueueWithProperties(context, device_id[0], 0, &ret);
  // kontekst, naprava, INORDER/OUTOFORDER, napake

  // Alokacija pomnilnika na napravi
  cl_mem a_mem_obj =
      clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                     hA * wA * sizeof(float), A, &ret);
  // kontekst, način, koliko, lokacija na hostu, napaka
  cl_mem b_mem_obj =
      clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                     hB * wB * sizeof(float), B, &ret);
  cl_mem c_mem_obj = clCreateBuffer(context, CL_MEM_WRITE_ONLY,
                                    hA * wB * sizeof(float), NULL, &ret);

  // Priprava programa
  cl_program program = clCreateProgramWithSource(
      context, 1, (const char **)&source_str, NULL, &ret);
  // kontekst, število kazalcev na kodo, kazalci na kodo,
  // stringi so NULL terminated, napaka

  // Prevajanje
  ret = clBuildProgram(program, 1, &device_id[0], NULL, NULL, NULL);
  // program, število naprav, lista naprav, opcije pri prevajanju,
  // kazalec na funkcijo, uporabniški argumenti

  // Log
  size_t build_log_len;
  char *build_log;
  ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG, 0,
                              NULL, &build_log_len);
  // program, naprava, tip izpisa,
  // maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
  build_log = (char *)malloc(sizeof(char) * (build_log_len + 1));
  ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG,
                              build_log_len, build_log, NULL);
  printf("%s\n", build_log);
  free(build_log);

  // ščepec: priprava objekta
  cl_kernel kernel = clCreateKernel(program, "matrixMultiply", &ret);
  // program, ime ščepca, napaka

  // ščepec: argumenti
  clSetKernelArg(kernel, 0, sizeof(cl_mem), (void *)&a_mem_obj);
  clSetKernelArg(kernel, 1, sizeof(cl_mem), (void *)&b_mem_obj);
  clSetKernelArg(kernel, 2, sizeof(cl_mem), (void *)&c_mem_obj);
  clSetKernelArg(kernel, 3, sizeof(cl_int), (void *)&hA);
  clSetKernelArg(kernel, 4, sizeof(cl_int), (void *)&wA);
  clSetKernelArg(kernel, 5, sizeof(cl_int), (void *)&hB);
  clSetKernelArg(kernel, 6, sizeof(cl_int), (void *)&wB);
  // ščepec, številka argumenta, velikost podatkov, kazalec na podatke

  // Delitev dela
  size_t local_item_size[2] = {WORKGROUP_SIZE, WORKGROUP_SIZE};
  size_t global_item_size[2] = {hA, wB};

  // ščepec: zagon
  ret = clEnqueueNDRangeKernel(command_queue, kernel, 2, NULL, global_item_size,
                               local_item_size, 0, NULL, NULL);
  // vrsta, ščepec, dimenzionalnost, mora biti NULL,
  // kazalec na število vseh niti, kazalec na lokalno število niti,
  // dogodki, ki se morajo zgoditi pred klicem

  // Kopiranje rezultatov
  ret = clEnqueueReadBuffer(command_queue, c_mem_obj, CL_TRUE, 0,
                            hA * wB * sizeof(int), C, 0, NULL, NULL);
  // branje v pomnilnik iz naparave, 0 = offset
  // zadnji trije - dogodki, ki se morajo zgoditi prej

  // Prikaz rezultatov
  // for (int i = 0; i < hA; i++)
  //   for (int j = 0; j < wB; j++) {
  //     float CC = 0.0f;
  //     for (int k = 0; k < wA; k++)
  //       CC += A[i * wA + k] * B[k * wB + j];
  //     if (fabs(C[i * wB + j] - CC) > 1e-3)
  //       printf("%f : %f\n", C[i * wB + j], CC);
  //   }

  // for (i = 0; i < hA; i++)
  //   for (j = 0; j < wB; j++)
  //     if (AreSame(C[i * wB + j], D[i * wB + j]) == false)
  //       printf("Error! %d %d: %f %f\n", i, j, C[i * wB + j], D[i * wB + j]);

  // čiščenje
  ret = clFlush(command_queue);
  ret = clFinish(command_queue);
  ret = clReleaseKernel(kernel);
  ret = clReleaseProgram(program);
  ret = clReleaseMemObject(a_mem_obj);
  ret = clReleaseMemObject(b_mem_obj);
  ret = clReleaseMemObject(c_mem_obj);
  ret = clReleaseCommandQueue(command_queue);
  ret = clReleaseContext(context);

  return C;
}
