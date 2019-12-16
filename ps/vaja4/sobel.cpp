#include <FreeImage.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

inline int getPixel(unsigned char *image, int width, int height, int y, int x) {
  int a = 0;
  if (x < 0 || x >= width)
    a = 0;
  if (y < 0 || y >= height)
    a = 0;
  else
    a = image[y * width + x];
  return a;
}

void sobelGPU(unsigned char *imageIn, unsigned char *imageOut, int width,
              int height);

void sobelCPU(unsigned char *imageIn, unsigned char *imageOut, int width,
              int height) {
  int i, j;
  int Gx, Gy;
  int tempPixel;

  // za vsak piksel v sliki
  for (i = 0; i < (height); i++)
    for (j = 0; j < (width); j++) {
      Gx = -getPixel(imageIn, width, height, i - 1, j - 1) -
           2 * getPixel(imageIn, width, height, i - 1, j) -
           getPixel(imageIn, width, height, i - 1, j + 1) +
           getPixel(imageIn, width, height, i + 1, j - 1) +
           2 * getPixel(imageIn, width, height, i + 1, j) +
           getPixel(imageIn, width, height, i + 1, j + 1);

      Gy = -getPixel(imageIn, width, height, i - 1, j - 1) -
           2 * getPixel(imageIn, width, height, i, j - 1) -
           getPixel(imageIn, width, height, i + 1, j - 1) +
           getPixel(imageIn, width, height, i - 1, j + 1) +
           2 * getPixel(imageIn, width, height, i, j + 1) +
           getPixel(imageIn, width, height, i + 1, j + 1);

      tempPixel = sqrt((float)(Gx * Gx + Gy * Gy));
      if (tempPixel > 255)
        imageOut[i * width + j] = 255;
      else
        imageOut[i * width + j] = tempPixel;
    }
}

unsigned char *runCPU(unsigned char *imageIn, int width, int height,
                      int pitch) {
  unsigned char *imageOut =
      (unsigned char *)malloc(height * width * sizeof(unsigned char));

  sobelCPU(imageIn, imageOut, width, height);

  FIBITMAP *imageOutBitmap = FreeImage_ConvertFromRawBits(
      imageOut, width, height, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
  FreeImage_Save(FIF_PNG, imageOutBitmap, "result_CPU.png", 0);
  FreeImage_Unload(imageOutBitmap);

  return imageOut;
}

unsigned char *runGPU(unsigned char *imageIn, int width, int height,
                      int pitch) {
  unsigned char *imageOut =
      (unsigned char *)malloc(height * width * sizeof(unsigned char));
  printf("%d %d %lu\n", width, height, height * width * sizeof(unsigned char));

  sobelGPU(imageIn, imageOut, width, height);

  FIBITMAP *imageOutBitmap = FreeImage_ConvertFromRawBits(
      imageOut, width, height, pitch, 8, 0xFF, 0xFF, 0xFF, TRUE);
  FreeImage_Save(FIF_PNG, imageOutBitmap, "result_GPU.png", 0);
  FreeImage_Unload(imageOutBitmap);

  return imageOut;
}

unsigned long long getTime() {
  struct timeval tv;

  gettimeofday(&tv, NULL);

  unsigned long long millisecondsSinceEpoch =
      (unsigned long long)(tv.tv_sec) * 1000 +
      (unsigned long long)(tv.tv_usec) / 1000;

  return millisecondsSinceEpoch;
}

int main() {
  unsigned char *slikaInput;
  unsigned char *slikaOutput;

  FIBITMAP *imageBitmap = FreeImage_Load(FIF_JPEG, "slika2.jpg", 0);
  if (imageBitmap == NULL) {
    printf("Error opening image!\n");
    return 0;
  }
  FIBITMAP *imageBitmapGrey = FreeImage_ConvertToGreyscale(imageBitmap);
  if (imageBitmapGrey == NULL) {
    printf("Error opening image!\n");
    return 0;
  }
  int width = FreeImage_GetWidth(imageBitmapGrey);
  int height = FreeImage_GetHeight(imageBitmapGrey);
  int pitch = FreeImage_GetPitch(imageBitmapGrey);

  unsigned char *imageIn =
      (unsigned char *)malloc(height * width * sizeof(unsigned char));

  FreeImage_ConvertToRawBits(imageIn, imageBitmapGrey, pitch, 8, 0xFF, 0xFF,
                             0xFF, TRUE);

  printf("Width: %d, height: %d, pitch: %d, %p\n", width, height, pitch,
         imageBitmapGrey);
  FreeImage_Unload(imageBitmapGrey);
  FreeImage_Unload(imageBitmap);

  unsigned long long a = getTime();
  unsigned char *ax = runCPU(imageIn, width, height, pitch);
  unsigned long long b = getTime();
  printf("CPU runtime: %llu\n", b - a);

  unsigned char *bx = runGPU(imageIn, width, height, pitch);

  // for (int i = 0; i < height; i++) {
  //   for (int j = 0; j < width; j++) {
  //     if (ax[i * width + j] != bx[i * width + j]) {
  //       printf("ERROR %d %d, ", i, j);
  //     }
  //   }
  // }

  free(imageIn);
}
