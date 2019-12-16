#include <CL/cl.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define WORKGROUP_SIZE 8
#define MAX_SOURCE_SIZE 16384

unsigned long long getTime();

int ceilDivide(int a, int b) {
  return (a + b - 1) / b;
}

int round_up(int num, int factor) {
  return num + factor - 1 - (num - 1) % factor;
}

float *longMultiply(int hA, int wA, int hB, int wB, float *A, float *B) {
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
  int groupCount = ceilDivide(hB, WORKGROUP_SIZE);
  int hX = hA;
  int wX = wB;
  float *X = (float *)malloc(hX * wX * groupCount * sizeof(float));

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
  cl_mem c_mem_obj = clCreateBuffer(context, CL_MEM_READ_WRITE,
                                    hX * wX * groupCount * sizeof(float), NULL, &ret);
  cl_mem x_mem_obj = clCreateBuffer(context, CL_MEM_WRITE_ONLY,
                                    hX * wX * sizeof(float), NULL, &ret);

  // Log
  size_t build_log_len;
  char *build_log;

  // Build program 1 ------------------------------------------------------------------------------
  // Priprava programa
  cl_program program1 = clCreateProgramWithSource(
      context, 1, (const char **)&source_str, NULL, &ret);
  // kontekst, število kazalcev na kodo, kazalci na kodo,
  // stringi so NULL terminated, napaka

  // Prevajanje
  ret = clBuildProgram(program1, 1, &device_id[0], NULL, NULL, NULL);
  // program, število naprav, lista naprav, opcije pri prevajanju,
  // kazalec na funkcijo, uporabniški argumenti

  ret = clGetProgramBuildInfo(program1, device_id[0], CL_PROGRAM_BUILD_LOG, 0,
                              NULL, &build_log_len);
  // program, naprava, tip izpisa,
  // maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
  build_log = (char *)malloc(sizeof(char) * (build_log_len + 1));
  ret = clGetProgramBuildInfo(program1, device_id[0], CL_PROGRAM_BUILD_LOG,
                              build_log_len, build_log, NULL);
  printf("Program 1: %s\n", build_log);
  free(build_log);

  // Build program 2 ------------------------------------------------------------------------------
  // Priprava programa
  cl_program program2 = clCreateProgramWithSource(
      context, 1, (const char **)&source_str, NULL, &ret);
  // kontekst, število kazalcev na kodo, kazalci na kodo,
  // stringi so NULL terminated, napaka

  // Prevajanje
  ret = clBuildProgram(program2, 1, &device_id[0], NULL, NULL, NULL);
  // program, število naprav, lista naprav, opcije pri prevajanju,
  // kazalec na funkcijo, uporabniški argumenti

  ret = clGetProgramBuildInfo(program2, device_id[0], CL_PROGRAM_BUILD_LOG, 0,
                              NULL, &build_log_len);
  // program, naprava, tip izpisa,
  // maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
  build_log = (char *)malloc(sizeof(char) * (build_log_len + 1));
  ret = clGetProgramBuildInfo(program2, device_id[0], CL_PROGRAM_BUILD_LOG,
                              build_log_len, build_log, NULL);
  printf("Program 2: %s\n", build_log);
  free(build_log);

  // Create kernel 1 ------------------------------------------------------------------------------
  // ščepec: priprava objekta
  cl_kernel kernel1 = clCreateKernel(program1, "longMultiply", &ret);
  // program, ime ščepca, napaka

  // ščepec: argumenti
  clSetKernelArg(kernel1, 0, sizeof(cl_mem), (void *)&a_mem_obj);
  clSetKernelArg(kernel1, 1, sizeof(cl_mem), (void *)&b_mem_obj);
  clSetKernelArg(kernel1, 2, sizeof(cl_mem), (void *)&c_mem_obj);
  clSetKernelArg(kernel1, 3, sizeof(cl_int), (void *)&hA);
  clSetKernelArg(kernel1, 4, sizeof(cl_int), (void *)&wA);
  clSetKernelArg(kernel1, 5, sizeof(cl_int), (void *)&hB);
  clSetKernelArg(kernel1, 6, sizeof(cl_int), (void *)&wB);
  // ščepec, številka argumenta, velikost podatkov, kazalec na podatke

  // Create kernel 2 ------------------------------------------------------------------------------
  // ščepec: priprava objekta
  cl_kernel kernel2 = clCreateKernel(program2, "sumMatrices", &ret);
  // program, ime ščepca, napaka

  // ščepec: argumenti
  int bufferTableWidth = wX * groupCount;
  clSetKernelArg(kernel2, 0, sizeof(cl_mem), (void *)&c_mem_obj);
  clSetKernelArg(kernel2, 1, sizeof(cl_mem), (void *)&x_mem_obj);
  clSetKernelArg(kernel2, 2, sizeof(cl_int), (void *)&hX);
  clSetKernelArg(kernel2, 3, sizeof(cl_int), (void *)&bufferTableWidth);
  clSetKernelArg(kernel2, 4, sizeof(cl_int), (void *)&hX);
  clSetKernelArg(kernel2, 5, sizeof(cl_int), (void *)&wX);
  // ščepec, številka argumenta, velikost podatkov, kazalec na podatke

  unsigned long long t1 = getTime();
  // Execute kernel 1 -----------------------------------------------------------------------------
  // Delitev dela
  size_t local_item_size[2] = {WORKGROUP_SIZE, WORKGROUP_SIZE};
  size_t global_item_size[2] = {WORKGROUP_SIZE, WORKGROUP_SIZE * groupCount};
  printf("CPU: %d %lu\n", groupCount, global_item_size[1]);

  // ščepec: zagon
  ret = clEnqueueNDRangeKernel(command_queue, kernel1, 2, NULL, global_item_size,
                               local_item_size, 0, NULL, NULL);
  // vrsta, ščepec, dimenzionalnost, mora biti NULL,
  // kazalec na število vseh niti, kazalec na lokalno število niti,
  // dogodki, ki se morajo zgoditi pred klicem

  printf("Kernel 1 done!\n");

  // Execute kernel 2 -----------------------------------------------------------------------------
  // Delitev dela
  global_item_size[0] = round_up(hX, WORKGROUP_SIZE);
  global_item_size[1] = round_up(wX, WORKGROUP_SIZE);
  printf("CPU: %d %lu %lu\n", groupCount, global_item_size[0], global_item_size[1]);

  // ščepec: zagon
  ret = clEnqueueNDRangeKernel(command_queue, kernel2, 2, NULL, global_item_size,
                               local_item_size, 0, NULL, NULL);
  // vrsta, ščepec, dimenzionalnost, mora biti NULL,
  // kazalec na število vseh niti, kazalec na lokalno število niti,
  // dogodki, ki se morajo zgoditi pred klicem
  

  // Copy work done -------------------------------------------------------------------------------
  // Kopiranje rezultatov
  ret = clEnqueueReadBuffer(command_queue, x_mem_obj, CL_TRUE, 0,
                            hX * wX * sizeof(int), X, 0, NULL, NULL);
  // branje v pomnilnik iz naparave, 0 = offset
  // zadnji trije - dogodki, ki se morajo zgoditi prej

  unsigned long long t2 = getTime();
  printf("Elapsed milis GPU: %llu\n", t2 - t1);

  // čiščenje
  ret = clFlush(command_queue);
  ret = clFinish(command_queue);
  ret = clReleaseKernel(kernel1);
  ret = clReleaseKernel(kernel2);
  ret = clReleaseProgram(program1);
  ret = clReleaseProgram(program2);
  ret = clReleaseMemObject(a_mem_obj);
  ret = clReleaseMemObject(b_mem_obj);
  ret = clReleaseMemObject(c_mem_obj);
  ret = clReleaseMemObject(x_mem_obj);
  ret = clReleaseCommandQueue(command_queue);
  ret = clReleaseContext(context);

  return X;
}