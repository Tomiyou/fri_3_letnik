#include <stdio.h>
#include <stdlib.h>
#include <FreeImage.h>
#include <math.h>
#include <time.h>

void printTime() {
	struct timespec ts;
	timespec_get(&ts, TIME_UTC);
	char buff[100];
	strftime(buff, sizeof buff, "%D %T", gmtime(&ts.tv_sec));
	printf("Current time: %s.%09ld UTC\n", buff, ts.tv_nsec);
}

void mandelbrotGPU(unsigned char *image, int height, int width, int image_value_count);

void mandelbrotCPU(unsigned char *image, int height, int width) {
	float x0, y0, x, y, xtemp;
	int i, j;
	int color;
	int iter;
	int max_iteration = 800;   //max stevilo iteracij
	unsigned char max = 255;   //max vrednost barvnega kanala

	//za vsak piksel v sliki							
	for (i = 0; i < height; i++)
		for (j = 0; j < width; j++)
		{
			x0 = (float)j / width * (float)3.5 - (float)2.5; //zacetna vrednost
			y0 = (float)i / height * (float)2.0 - (float)1.0;
			x = 0;
			y = 0;
			iter = 0;
			//ponavljamo, dokler ne izpolnemo enega izmed pogojev
			while ((x*x + y * y <= 4) && (iter < max_iteration))
			{
				xtemp = x * x - y * y + x0;
				y = 2 * x*y + y0;
				x = xtemp;
				iter++;
			}
			//izracunamo barvo (magic: http://linas.org/art-gallery/escape/smooth.html)
			color = 1.0 + iter - log(log(sqrt(x*x + y * y))) / log(2.0);
			color = (8 * max * color) / max_iteration;
			if (color > max)
				color = max;
			//zapisemo barvo RGBA (v resnici little endian BGRA)
			image[4 * i*width + 4 * j + 0] = color; //Blue
			image[4 * i*width + 4 * j + 1] = color; // Green
			image[4 * i*width + 4 * j + 2] = color; // Red
			image[4 * i*width + 4 * j + 3] = 255;   // Alpha
		}
}

int runCPU(int height, int width, int pitch) {
	//rezerviramo prostor za sliko (RGBA)
	int image_value_count = height * width * 4;
	unsigned char *image = (unsigned char *)malloc(image_value_count * sizeof(unsigned char));

	mandelbrotCPU(image, height, width);

	//shranimo sliko
	FIBITMAP *dst = FreeImage_ConvertFromRawBits(image, width, height, pitch,
		32, FI_RGBA_RED_MASK, FI_RGBA_GREEN_MASK, FI_RGBA_BLUE_MASK, TRUE);
	FreeImage_Save(FIF_PNG, dst, "mandelbrot_cpu.png", 0);
	return 0;
}

int runGPU(int height, int width, int pitch) {
	//rezerviramo prostor za sliko (RGBA)
	int image_value_count = height * width * 4;
	unsigned char *image = (unsigned char *)malloc(image_value_count * sizeof(unsigned char));

	mandelbrotGPU(image, height, width, image_value_count);

	//shranimo sliko
	FIBITMAP *dst = FreeImage_ConvertFromRawBits(image, width, height, pitch,
		32, FI_RGBA_RED_MASK, FI_RGBA_GREEN_MASK, FI_RGBA_BLUE_MASK, TRUE);
	FreeImage_Save(FIF_PNG, dst, "mandelbrot_gpu.png", 0);
	return 0;
}

int main(void) {
	int height = 4096;
	int width = 4096;
	int pitch = ((32 * width + 31) / 32) * 4;

	printTime();
	runCPU(height, width, pitch);
	printTime();
	runGPU(height, width, pitch);
	printTime();
}

/*

4096x4096
- 14:23:33.478401655 UTC
- 14:23:57.653843382 UTC ~ 24.175441727 s
Uporabljam GPU.
- 14:24:00.570516736 UTC ~ 2.916673354 s


2048x2048
- 14:25:54.499900894 UTC
- 14:26:00.663249878 UTC ~ 6.163348984 s
Uporabljam GPU.
- 14:26:02.480083643 UTC ~ 1.816833773 s


1500x1500
- 14:27:59.422866101 UTC
- 14:28:02.755635631 UTC ~ 3.33276953 s
Uporabljam GPU.
- 14:28:04.428374831 UTC ~ 1.6727392 s

1000x1000
- 14:38:34.769042686 UTC
- 14:38:36.314886144 UTC ~ 1.545843458 s
Uporabljam GPU.
- 14:38:37.873345960 UTC ~ 1.558459816 s

*/
