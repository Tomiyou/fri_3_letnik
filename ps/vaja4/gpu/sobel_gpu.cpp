#include <CL/cl.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define LOCAL_SIZE 8
#define MAX_SOURCE_SIZE 16384

unsigned long long getTime();

size_t CEIL(int v) {
  return LOCAL_SIZE * (size_t)ceil(v / (double)LOCAL_SIZE);
}

void sobelGPU(unsigned char *imageIn, unsigned char *imageOut, int width,
              int height) {
  printf("Uporabljam GPU za sobel. ----------------------------------------------\n");
  char ch;
  int i, j;
  cl_int ret;

  // Branje datoteke
  FILE *fp;
  char *source_str;
  size_t source_size;

  fp = fopen("gpu/kernel.cl", "r");
  if (!fp) {
    fprintf(stderr, ":-(#\n");
    exit(1);
  }
  source_str = (char *)malloc(MAX_SOURCE_SIZE);
  source_size = fread(source_str, 1, MAX_SOURCE_SIZE, fp);
  source_str[source_size] = '\0';
  fclose(fp);

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
  cl_mem A_mem_obj =
      clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                     width * height * sizeof(*imageIn), imageIn, &ret);
  cl_mem B_mem_obj =
      clCreateBuffer(context, CL_MEM_WRITE_ONLY,
                     width * height * sizeof(*imageOut), NULL, &ret);
  printf("%d %d %lu\n", width, height, width * height * sizeof(*imageOut));

  // Priprava programa
  cl_program program = clCreateProgramWithSource(
      context, 1, (const char **)&source_str, NULL, &ret);
  free(source_str);
  printf("OK?");
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
  // program, "naprava, tip izpisa,
  // maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
  build_log = (char *)malloc(sizeof(char) * (build_log_len + 1));
  ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG,
                              build_log_len, build_log, NULL);
  printf("%s\n", build_log);
  free(build_log);

  // printf("Continue?");
  // scanf("%c", &ch);

  // ščepec: priprava objekta
  cl_kernel kernel = clCreateKernel(program, "sobel", &ret);
  printf("Ret: %d\n", ret);
  // program, ime ščepca, napaka

  // ščepec: argumenti
  int local_mem_size = LOCAL_SIZE + 2;
  clSetKernelArg(kernel, 0, sizeof(cl_mem), (void *)&A_mem_obj);
  printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 1, sizeof(cl_mem), (void *)&B_mem_obj);
  printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 2, sizeof(cl_int), (void *)&height);
  printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 3, sizeof(cl_int), (void *)&width);
  printf("Ret: %d\n", ret);
  ret |= clSetKernelArg(
      kernel, 4, local_mem_size * local_mem_size * sizeof(*imageIn), NULL);
  printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 5, sizeof(cl_int), (void *)&local_mem_size);
  printf("Ret: %d\n", ret);
  // ščepec, številka argumenta, velikost podatkov, kazalec na podatke

  // Delitev dela
  size_t local_item_size[2] = {LOCAL_SIZE, LOCAL_SIZE};
  size_t global_item_size[2] = {CEIL(height), CEIL(width)};
  printf("%lu %lu\n", global_item_size[0], global_item_size[1]);
  // size_t global_item_size[2] = {GLOBAL_SIZE, GLOBAL_SIZE};

  // ščepec: zagon
  unsigned long long a = getTime();
  ret = clEnqueueNDRangeKernel(command_queue, kernel, 2, NULL, global_item_size,
                               local_item_size, 0, NULL, NULL);
  printf("Ret: %d\n", ret);
  // vrsta, ščepec, dimenzionalnost, mora biti NULL,
  // kazalec na število vseh niti, kazalec na lokalno število niti,
  // dogodki, ki se morajo zgoditi pred klicem

  // Kopiranje rezultatov
  ret = clEnqueueReadBuffer(command_queue, B_mem_obj, CL_TRUE, 0,
                            width * height * sizeof(*imageOut), imageOut, 0,
                            NULL, NULL);
  unsigned long long b = getTime();
  printf("Ret: %d\n", ret);
  printf("GPU runtime: %llu\n", b - a);
  // branje v pomnilnik iz naparave, 0 = offset
  // zadnji trije - dogodki, ki se morajo zgoditi prej



  // for (int i = 0; i < height; i++) {
  //   printf("%d, ", imageOut[i * width]);
  // }
  // printf("\n");

  // čiščenje
  ret = clFlush(command_queue);
  printf("Ret: %d\n", ret);
  ret = clFinish(command_queue);
  ret = clReleaseKernel(kernel);
  ret = clReleaseProgram(program);
  ret = clReleaseMemObject(A_mem_obj);
  ret = clReleaseMemObject(B_mem_obj);
  ret = clReleaseCommandQueue(command_queue);
  ret = clReleaseContext(context);
}
