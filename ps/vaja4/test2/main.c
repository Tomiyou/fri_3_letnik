#define CL_TARGET_OPENCL_VERSION 220

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <CL/cl.h>
#include <FreeImage.h>

#define WORKGROUP_SIZE  (32)
#define MAX_SOURCE_SIZE 16384

int main(void) 
{
    cl_int ret;
    FILE *fp;
    char *source_str;
    size_t source_size;


    //FreeImage_Initialise(0);
    printf("%s\n",FreeImage_GetVersion());

    FIBITMAP *imageBitmap = FreeImage_Load(FIF_JPEG, "slika2.jpg", JPEG_ACCURATE);
    printf("%d,  %d\n",FreeImage_GetWidth(imageBitmap),FreeImage_GetHeight(imageBitmap));
    FIBITMAP *imageBitmapGrey = FreeImage_ConvertToGreyscale(imageBitmap);
    int width = FreeImage_GetWidth(imageBitmapGrey);
    int height = FreeImage_GetHeight(imageBitmapGrey);
    int pitch = FreeImage_GetPitch(imageBitmapGrey);


    unsigned char *imageIn = (unsigned char*)malloc(height*width * sizeof(unsigned char));
    unsigned char *imageOut = (unsigned char*)malloc(height*width * sizeof(unsigned char));

    FreeImage_ConvertToRawBits(imageIn, imageBitmapGrey, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
    //FreeImage_ConvertToRawBits(imageIn, imageBitmap, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
    //printf("val:%d\n", imageIn[0]);
    FreeImage_Unload(imageBitmapGrey);
    FreeImage_Unload(imageBitmap);




    fp = fopen("kernel.cl", "r");
    if (!fp) 
    {
        fprintf(stderr, ":-(#\n");
        exit(1);
    }
    source_str = (char*)malloc(MAX_SOURCE_SIZE);
    source_size = fread(source_str, 1, MAX_SOURCE_SIZE, fp);
    source_str[source_size] = '\0';
    fclose( fp );

    // Podatki o platformi
    cl_platform_id  platform_id[10];
    cl_uint         ret_num_platforms;
    char            *buf;
    size_t          buf_len;
    ret = clGetPlatformIDs(10, platform_id, &ret_num_platforms);
            // max. "stevilo platform, kazalec na platforme, dejansko "stevilo platform
    
    cl_device_id    device_id[10];
    cl_uint         ret_num_devices;
    ret = clGetDeviceIDs(platform_id[0], CL_DEVICE_TYPE_GPU, 10, device_id, &ret_num_devices);              
            // izbrana platforma, tip naprave, koliko naprav nas zanima
            // kazalec na naprave, dejansko "stevilo naprav

    // Kontekst
    cl_context context = clCreateContext(NULL, 1, &device_id[0], NULL, NULL, &ret);
            // kontekst: vklju"cene platforme - NULL je privzeta, "stevilo naprav, 
            // kazalci na naprave, kazalec na call-back funkcijo v primeru napake
            // dodatni parametri funkcije, "stevilka napake
 
    // Ukazna vrsta
    cl_command_queue command_queue = clCreateCommandQueueWithProperties(context, device_id[0], 0, &ret);
            // kontekst, naprava, INORDER/OUTOFORDER, napake

    // Priprava programa
    cl_program program = clCreateProgramWithSource(context, 1, (const char **)&source_str, NULL, &ret);
            // kontekst, "stevilo kazalcev na kodo, kazalci na kodo,        
            // stringi so NULL terminated, napaka                                                   
 
    ret = clBuildProgram(program, 1, &device_id[0], NULL, NULL, NULL);
            // program, "stevilo naprav, lista naprav, opcije pri prevajanju,
            // kazalec na funkcijo, uporabni"ski argumenti

    size_t build_log_len;
    char *build_log;
    ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG, 0, NULL, &build_log_len);
            // program, "naprava, tip izpisa, 
            // maksimalna dol"zina niza, kazalec na niz, dejanska dol"zina niza
    build_log =(char *)malloc(sizeof(char)*(build_log_len+1));
    ret = clGetProgramBuildInfo(program, device_id[0], CL_PROGRAM_BUILD_LOG, 
                                build_log_len, build_log, NULL);
    printf("%s\n", build_log);
    free(build_log);

    // Delitev dela     
    cl_mem imageIn_mem_obj = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, height*width * sizeof(unsigned char), imageIn, &ret);
    cl_mem imageOut_mem_obj = clCreateBuffer(context, CL_MEM_WRITE_ONLY, height*width * sizeof(unsigned char), NULL, &ret);
    
    // program, ime "s"cepca, napaka
    cl_kernel kernel = clCreateKernel(program, "dotProduct", &ret);

    ret = clSetKernelArg(kernel, 0, sizeof(cl_mem), (void *)&imageIn_mem_obj);
    ret |= clSetKernelArg(kernel, 1, sizeof(cl_mem), (void *)&imageOut_mem_obj);
    ret |= clSetKernelArg(kernel, 2, sizeof(cl_int), (void *)&width);
    ret |= clSetKernelArg(kernel, 3, sizeof(cl_int), (void *)&height);
        // "s"cepec, "stevilka argumenta, velikost podatkov, kazalec na podatke         


    size_t local_item_size[2] = { 32, 1};
    size_t global_item_size[2] = { width+WORKGROUP_SIZE-(width%WORKGROUP_SIZE), height+WORKGROUP_SIZE-(height%WORKGROUP_SIZE) };

    //printf("global_size:%d,  %d\n", width+WORKGROUP_SIZE-(width%WORKGROUP_SIZE), height+WORKGROUP_SIZE-(height%WORKGROUP_SIZE) );

    //size_t local_item_size[2] = { 32, 1};
    //size_t global_item_size[2] = { 32, 2 };


    ret = clEnqueueNDRangeKernel(command_queue, kernel, 2, NULL, global_item_size, local_item_size, 0, NULL, NULL); 
            // vrsta, "s"cepec, dimenzionalnost, mora biti NULL, 
            // kazalec na "stevilo vseh niti, kazalec na lokalno "stevilo niti, 
            // dogodki, ki se morajo zgoditi pred klicem


    clFinish(command_queue);

    // Kopiranje rezultatov
    ret = clEnqueueReadBuffer(command_queue, imageOut_mem_obj, CL_TRUE, 0, height*width * sizeof(unsigned char), (void*)imageOut, 0, NULL, NULL);              
            // branje v pomnilnik iz naparave, 0 = offset
            // zadnji trije - dogodki, ki se morajo zgoditi prej


    FIBITMAP *imageOutBitmap = FreeImage_ConvertFromRawBits(imageOut, width, height, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
    FreeImage_Save(FIF_JPEG, imageOutBitmap, "sobel_slika.jpg", 0);
    FreeImage_Unload(imageOutBitmap);


    ret = clFlush(command_queue);
    ret = clFinish(command_queue);
    ret = clReleaseKernel(kernel);
    ret = clReleaseProgram(program);
    ret = clReleaseMemObject(imageIn_mem_obj);
    ret = clReleaseMemObject(imageOut_mem_obj);
    ret = clReleaseCommandQueue(command_queue);
    ret = clReleaseContext(context);
    //free(imageOut);

    return 0;
}