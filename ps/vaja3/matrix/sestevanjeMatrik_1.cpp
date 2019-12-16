#include <CL/cl.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


#define SIZE 512
#define WORKGROUP_SIZE 16
#define MAX_SOURCE_SIZE 16384

int main(void) {
  char ch;
  int i, j;
  cl_int ret;

  int h = SIZE;
  int w = SIZE;

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
  float *A = (float *)malloc(h * w * sizeof(float));
  float *B = (float *)malloc(h * w * sizeof(float));
  float *C = (float *)malloc(h * w * sizeof(float));

  // Inicializacija matri
  srand((int)time(NULL));
  for (i = 0; i < h; i++)
    for (j = 0; j < w; j++)
      A[i * w + j] = rand() / (float)RAND_MAX;
  for (i = 0; i < h; i++)
    for (j = 0; j < w; j++)
      B[i * w + j] = rand() / (float)RAND_MAX;

  // Podatki o platformi
  cl_platform_id platform_id[10];
  cl_uint ret_num_platforms;
  char *buf;
  size_t buf_len;
  ret = clGetPlatformIDs(10, platform_id, &ret_num_platforms);
  // max. "stevilo platform, kazalec na platforme, dejansko "stevilo platform

  // Podatki o napravi
  cl_device_id device_id[10];
  cl_uint ret_num_devices;
  // Delali bomo s platform_id[0] na GPU
  ret = clGetDeviceIDs(platform_id[0], CL_DEVICE_TYPE_GPU, 10, device_id,
                       &ret_num_devices);
  // izbrana platforma, tip naprave, koliko naprav nas zanima
  // kazalec na naprave, dejansko "stevilo naprav

  // Kontekst
  cl_context context =
      clCreateContext(NULL, 1, &device_id[0], NULL, NULL, &ret);
  // kontekst: vklju"cene platforme - NULL je privzeta, "stevilo naprav,
  // kazalci na naprave, kazalec na call-back funkcijo v primeru napake
  // dodatni parametri funkcije, "stevilka napake

  // Ukazna vrsta
  cl_command_queue command_queue =
      clCreateCommandQueue(context, device_id[0], 0, &ret);
  // kontekst, naprava, INORDER/OUTOFORDER, napake

  // Alokacija pomnilnika na napravi
  cl_mem a_mem_obj =
      clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                     h * w * sizeof(float), A, &ret);
  // kontekst, na"cin, koliko, lokacija na hostu, napaka
  cl_mem b_mem_obj =
      clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                     h * w * sizeof(float), B, &ret);
  cl_mem c_mem_obj = clCreateBuffer(context, CL_MEM_WRITE_ONLY,
                                    h * w * sizeof(float), NULL, &ret);

  // Priprava programa
  cl_program program = clCreateProgramWithSource(
      context, 1, (const char **)&source_str, NULL, &ret);
  // kontekst, "stevilo kazalcev na kodo, kazalci na kodo,
  // stringi so NULL terminated, napaka

  // Prevajanje
  ret = clBuildProgram(program, 1, &device_id[0], NULL, NULL, NULL);
  // program, "stevilo naprav, lista naprav, opcije pri prevajanju,
  // kazalec na funkcijo, uporabni"ski argumenti

  // Log
  size_t build_log_len;
  char *build_log;
  ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG, 0,
                              NULL, &build_log_len);
	printf("Ret: %d\n", ret);
  // program, "naprava, tip izpisa,
  // maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
  build_log = (char *)malloc(sizeof(char) * (build_log_len + 1));
  ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG,
                              build_log_len, build_log, NULL);
	printf("Ret: %d\n", ret);
  printf("%s\n", build_log);
  free(build_log);

  scanf("%c", &ch);

  // "s"cepec: priprava objekta
  cl_kernel kernel = clCreateKernel(program, "matrixAdd", &ret);
	printf("Ret: %d\n", ret);
  // program, ime "s"cepca, napaka

  // "s"cepec: argumenti
  clSetKernelArg(kernel, 0, sizeof(cl_mem), (void *)&a_mem_obj);
	printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 1, sizeof(cl_mem), (void *)&b_mem_obj);
	printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 2, sizeof(cl_mem), (void *)&c_mem_obj);
	printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 3, sizeof(cl_int), (void *)&h);
	printf("Ret: %d\n", ret);
  clSetKernelArg(kernel, 4, sizeof(cl_int), (void *)&w);
	printf("Ret: %d\n", ret);
  // "s"cepec, "stevilka argumenta, velikost podatkov, kazalec na podatke

  // Delitev dela
  size_t local_item_size[2] = {WORKGROUP_SIZE, WORKGROUP_SIZE};
  size_t global_item_size[2] = {SIZE, SIZE};
  // size_t global_item_size[2] = {(size_t)h, (size_t)w};

  // "s"cepec: zagon
  ret = clEnqueueNDRangeKernel(command_queue, kernel, 2, NULL, global_item_size,
                               local_item_size, 0, NULL, NULL);
	printf("Ret: %d\n", ret);
  // vrsta, "s"cepec, dimenzionalnost, mora biti NULL,
  // kazalec na "stevilo vseh niti, kazalec na lokalno "stevilo niti,
  // dogodki, ki se morajo zgoditi pred klicem

  // Kopiranje rezultatov
  ret = clEnqueueReadBuffer(command_queue, c_mem_obj, CL_TRUE, 0,
                            h * w * sizeof(int), C, 0, NULL, NULL);
	printf("Ret: %d\n", ret);
  // branje v pomnilnik iz naparave, 0 = offset
  // zadnji trije - dogodki, ki se morajo zgoditi prej

  // Prikaz rezultatov
  for (i = 0; i < h; i++)
    for (j = 0; j < w; j++) {
      float CC = A[i * w + j] + B[i * w + j];
      if (fabs(C[i * w + j] - CC) > 1e-3)
        printf("%f : %f\n", C[i * w + j], CC);
    }

  // "ci"s"cenje
  ret = clFlush(command_queue);
	printf("Ret: %d\n", ret);
  ret = clFinish(command_queue);
  ret = clReleaseKernel(kernel);
  ret = clReleaseProgram(program);
  ret = clReleaseMemObject(a_mem_obj);
  ret = clReleaseMemObject(b_mem_obj);
  ret = clReleaseMemObject(c_mem_obj);
  ret = clReleaseCommandQueue(command_queue);
  ret = clReleaseContext(context);

  free(A);
  free(B);
  free(C);

  return 0;
}
