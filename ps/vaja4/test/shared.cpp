// "s"cepec preberemo iz datoteke

#include <stdio.h>
#include <stdlib.h>
#include <CL/cl.h>
#include "FreeImage.h"
#include <math.h>
#include <time.h> 
#include <chrono>

#define SIZE			(1024)
#define WORKGROUP_SIZE	(8)
#define MAX_SOURCE_SIZE	16384

size_t CEIL(int v) {
  return WORKGROUP_SIZE * (size_t)ceil(v / (double)WORKGROUP_SIZE);
}

inline int getPixel(unsigned char *image, int width, int height, int y, int x)
{
	if (x < 0 || x >= width)
		return 0;
	if (y < 0 || y >= height)
		return 0;
	return image[y*width + x];
}

void sobelCPU(unsigned char *imageIn, unsigned char *imageOut, int width, int height)
{
	int i, j;
	int Gx, Gy;
	int tempPixel;
	auto t1 = std::chrono::high_resolution_clock::now();
	//za vsak piksel v sliki
	for (i = 0; i < (height); i++)
		for (j = 0; j < (width); j++)
		{
			Gx = -getPixel(imageIn, width, height, i - 1, j - 1) - 2 * getPixel(imageIn, width, height, i - 1, j) -
				getPixel(imageIn, width, height, i - 1, j + 1) + getPixel(imageIn, width, height, i + 1, j - 1) +
				2 * getPixel(imageIn, width, height, i + 1, j) + getPixel(imageIn, width, height, i + 1, j + 1);
			Gy = -getPixel(imageIn, width, height, i - 1, j - 1) - 2 * getPixel(imageIn, width, height, i, j - 1) -
				getPixel(imageIn, width, height, i + 1, j - 1) + getPixel(imageIn, width, height, i - 1, j + 1) +
				2 * getPixel(imageIn, width, height, i, j + 1) + getPixel(imageIn, width, height, i + 1, j + 1);
			tempPixel = sqrt((float)(Gx * Gx + Gy * Gy));
			if (tempPixel > 255)
				imageOut[i*width + j] = 255;
			else
				imageOut[i*width + j] = tempPixel;
		}
	auto t2 = std::chrono::high_resolution_clock::now();
	auto duration = std::chrono::duration_cast<std::chrono::microseconds>(t2 - t1).count();
	float cas = (float)(duration)/1000000;
	printf("%f\n", cas);
}
void sobelInitCpu(void) {
	unsigned char *slikaInput;
	unsigned char *slikaOutput;

	FIBITMAP *imageBitmap = FreeImage_Load(FIF_JPEG, "slika2.jpg", 0);
	FIBITMAP *imageBitmapGrey = FreeImage_ConvertToGreyscale(imageBitmap);
	int width = FreeImage_GetWidth(imageBitmapGrey);
	int height = FreeImage_GetHeight(imageBitmapGrey);
	int pitch = FreeImage_GetPitch(imageBitmapGrey);

	unsigned char *imageIn = (unsigned char*)malloc(height*width * sizeof(unsigned char));
	unsigned char *imageOut = (unsigned char*)malloc(height*width * sizeof(unsigned char));

	FreeImage_ConvertToRawBits(imageIn, imageBitmapGrey, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);

	FreeImage_Unload(imageBitmapGrey);
	FreeImage_Unload(imageBitmap);


	sobelCPU(imageIn, imageOut, width, height);
	FIBITMAP *imageOutBitmap = FreeImage_ConvertFromRawBits(imageOut, width, height, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
	FreeImage_Save(FIF_PNG, imageOutBitmap, "sobel_slika.png", 0);
	FreeImage_Unload(imageOutBitmap);

	
}

void sobelGPU(unsigned char *imageIn, unsigned char *imageOut, int width, int height) {
	char ch;
	int i;
	cl_int ret;

	int pitch = ((32 * width + 31) / 32) * 4;

	int vectorSize = width * height;

	// Branje datoteke
	FILE *fp;
	char *source_str;
	size_t source_size;
	int wkSize = WORKGROUP_SIZE;
	fp = fopen("kernel.cl", "r");
	if (!fp)
	{
		fprintf(stderr, ":-(#\n");
		exit(1);
	}
	source_str = (char*)malloc(MAX_SOURCE_SIZE);
	source_size = fread(source_str, 1, MAX_SOURCE_SIZE, fp);
	source_str[source_size] = '\0';
	fclose(fp);

	// Podatki o platformi
	cl_platform_id	platform_id[10];
	cl_uint			ret_num_platforms;
	char			*buf;
	size_t			buf_len;
	ret = clGetPlatformIDs(10, platform_id, &ret_num_platforms);
  printf("%d ||", ret);
	// max. "stevilo platform, kazalec na platforme, dejansko "stevilo platform

// Podatki o napravi
	cl_device_id	device_id[10];
	cl_uint			ret_num_devices;
	// Delali bomo s platform_id[0] na GPU
	ret = clGetDeviceIDs(platform_id[0], CL_DEVICE_TYPE_GPU, 10,
		device_id, &ret_num_devices);
	// izbrana platforma, tip naprave, koliko naprav nas zanima
	// kazalec na naprave, dejansko "stevilo naprav
  printf("%d ||", ret);

// Kontekst
	cl_context context = clCreateContext(NULL, 1, &device_id[0], NULL, NULL, &ret);
	// kontekst: vklju"cene platforme - NULL je privzeta, "stevilo naprav, 
	// kazalci na naprave, kazalec na call-back funkcijo v primeru napake
	// dodatni parametri funkcije, "stevilka napake
  printf("%d ||", ret);

// Ukazna vrsta
	cl_command_queue command_queue = clCreateCommandQueue(context, device_id[0], 0, &ret);
	// kontekst, naprava, INORDER/OUTOFORDER, napake
  printf("%d ||", ret);

// Delitev dela
	size_t local_item_size[2] = { WORKGROUP_SIZE,WORKGROUP_SIZE };
	//size_t num_groups = ((vectorSize - 1) / local_item_size + 1);
	size_t global_item_size[2] = { CEIL(width), CEIL(height) };


	cl_mem in_mem_obj = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
		vectorSize * sizeof(unsigned char), imageIn, &ret);
	// kontekst, na"cin, koliko, lokacija na hostu, napaka	
  printf("%d ||", ret);
	cl_mem out_mem_obj = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
		vectorSize * sizeof(unsigned char), imageOut, &ret);
    printf("%d ||", ret);

	// Priprava programa
	cl_program program = clCreateProgramWithSource(context, 1, (const char **)&source_str,
		NULL, &ret);
	// kontekst, "stevilo kazalcev na kodo, kazalci na kodo,		
	// stringi so NULL terminated, napaka													

printf("%d ||", ret);
// Prevajanje
	ret = clBuildProgram(program, 1, &device_id[0], NULL, NULL, NULL);
	// program, "stevilo naprav, lista naprav, opcije pri prevajanju,
	// kazalec na funkcijo, uporabni"ski argumenti
printf("%d ||", ret);
// Log
	size_t build_log_len;
	char *build_log;
	ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG,
		0, NULL, &build_log_len);
	// program, "naprava, tip izpisa, 
  printf("%d ||", ret);
	// maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
	build_log = (char *)malloc(sizeof(char)*(build_log_len + 1));
	ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG,
		build_log_len, build_log, NULL);
	printf("%s\n", build_log);
	free(build_log);
printf("%d ||", ret);
	// "s"cepec: priprava objekta
	cl_kernel kernel = clCreateKernel(program, "sobel", &ret);
	// program, ime "s"cepca, napaka
  printf("%d ||", ret);

	size_t buf_size_t;
	clGetKernelWorkGroupInfo(kernel, device_id[0], CL_KERNEL_PREFERRED_WORK_GROUP_SIZE_MULTIPLE, sizeof(buf_size_t), &buf_size_t, NULL);
	printf("veckratnik niti = %d", buf_size_t);

	// scanf("%c", &ch);

	// "s"cepec: argumenti
	ret = clSetKernelArg(kernel, 0, sizeof(cl_mem), (void *)&in_mem_obj);
	ret |= clSetKernelArg(kernel, 1, sizeof(cl_mem), (void *)&out_mem_obj);
	ret |= clSetKernelArg(kernel, 2, sizeof(cl_int), (void *)&width);
	ret |= clSetKernelArg(kernel, 3, sizeof(cl_int), (void *)&height);
	ret |= clSetKernelArg(kernel, 4, sizeof(cl_int), (void *)&wkSize);
  printf("%d ||", ret);
	// "s"cepec, "stevilka argumenta, velikost podatkov, kazalec na podatke


	auto t1 = std::chrono::high_resolution_clock::now();
	// "s"cepec: zagon
	ret = clEnqueueNDRangeKernel(command_queue, kernel, 2, NULL,
		global_item_size, local_item_size, 0, NULL, NULL);
	// vrsta, "s"cepec, dimenzionalnost, mora biti NULL, 
	// kazalec na "stevilo vseh niti, kazalec na lokalno "stevilo niti, 
	// dogodki, ki se morajo zgoditi pred klicem
  printf("__%d ||", ret);

// Kopiranje rezultatov
	ret = clEnqueueReadBuffer(command_queue, out_mem_obj, CL_TRUE, 0,
		vectorSize * sizeof(unsigned char), imageOut, 0, NULL, NULL);
	// branje v pomnilnik iz naparave, 0 = offset
	// zadnji trije - dogodki, ki se morajo zgoditi prej
  printf("%d ||", ret);

// Prikaz rezultatov
	/*for (int i = 0;i < velikostTabele;i++)
		printf("%d\n", C[i]);*/
	auto t2 = std::chrono::high_resolution_clock::now();
	auto duration = std::chrono::duration_cast<std::chrono::microseconds>(t2 - t1).count();
	float cas = (float)(duration) / 1000000;
	printf("%f\n", cas);

	

	// "ci"s"cenje
	ret = clFlush(command_queue);
	ret = clFinish(command_queue);
	ret = clReleaseKernel(kernel);
	ret = clReleaseProgram(program);
	ret = clReleaseMemObject(out_mem_obj);
	ret = clReleaseCommandQueue(command_queue);
	ret = clReleaseContext(context);

}
void sobelInitGPU(void) {
	unsigned char *slikaInput;
	unsigned char *slikaOutput;

	FIBITMAP *imageBitmap = FreeImage_Load(FIF_JPEG, "slika2.jpg", 0);
	FIBITMAP *imageBitmapGrey = FreeImage_ConvertToGreyscale(imageBitmap);
	int width = FreeImage_GetWidth(imageBitmapGrey);
	int height = FreeImage_GetHeight(imageBitmapGrey);
	int pitch = FreeImage_GetPitch(imageBitmapGrey);

	unsigned char *imageIn = (unsigned char*)malloc(height*width * sizeof(unsigned char));
	unsigned char *imageOut = (unsigned char*)malloc(height*width * sizeof(unsigned char));

	FreeImage_ConvertToRawBits(imageIn, imageBitmapGrey, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);

	FreeImage_Unload(imageBitmapGrey);
	FreeImage_Unload(imageBitmap);


	sobelGPU(imageIn, imageOut, width, height);

  printf("xfxfxf\n");

	FIBITMAP *imageOutBitmap = FreeImage_ConvertFromRawBits(imageOut, width, height, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
	FreeImage_Save(FIF_PNG, imageOutBitmap, "sobel_slikaGPU.png", 0);
	FreeImage_Unload(imageOutBitmap);


}

int main(void)
{
	sobelInitCpu();
	sobelInitGPU();
	// system("pause");
}




