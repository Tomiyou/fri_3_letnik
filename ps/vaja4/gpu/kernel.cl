inline int getPixel(__global unsigned char *image, int width, int height, int y, int x) {
  int a = 0;
  if (x < 0 || x >= width)
    a = 0;
  if (y < 0 || y >= height)
    a = 0;
  else
    a = image[y * width + x];
  return a;
}

inline int getPixel2(__local unsigned char *image, int size, int y, int x) {
    return image[y * size + x];
}

__kernel void sobel(__global unsigned char *imageIn,
                    __global unsigned char *imageOut,
                    int height,
                    int width,
										__local unsigned char *okolica,
										int local_mem_size)
{
	int ly = get_local_id(0), lx = get_local_id(1);
	int gy = get_global_id(0), gx = get_global_id(1);

	int i = ly + 1, j = lx + 1;
	int last_idx = local_mem_size - 2;

	okolica[i * local_mem_size + j] = getPixel(imageIn, width, height, gy, gx);

	if (i == 1) {
		okolica[j] = getPixel(imageIn, width, height, gy-1, gx);
	}
	if (j == 1) {
		okolica[i * local_mem_size] = getPixel(imageIn, width, height, gy, gx-1);
	}

	if (i == last_idx) {
		okolica[(i+1) * local_mem_size + j] = getPixel(imageIn, width, height, gy+1, gx);
	}
	if (j == last_idx) {
		okolica[i * local_mem_size + (j+1)] = getPixel(imageIn, width, height, gy, gx+1);
	}

	// upper left
	if (j == 1 && i == 1) {
		okolica[0] = getPixel(imageIn, width, height, gy-1, gx-1);
	}
	// lower left
	if (j == 1 && i == last_idx) {
		okolica[(i + 1) * local_mem_size] = getPixel(imageIn, width, height, gy+1, gx-1);
	}
	// upper right
	if (j == last_idx && i == 1) {
		okolica[(j+1)] = getPixel(imageIn, width, height, gy-1, gx+1);
	}
	// lower right
	if (j == last_idx && i == last_idx) {
		okolica[(i + 1) * local_mem_size + (j+1)] = getPixel(imageIn, width, height, gy+1, gx+1);
	}

	barrier(CLK_LOCAL_MEM_FENCE);

	if (gy < height && gx < width) {
		int Gx, Gy;
		int tempPixel;

		Gx = -getPixel2(okolica, local_mem_size, i - 1, j - 1) -
           2 * getPixel2(okolica, local_mem_size, i - 1, j) -
           getPixel2(okolica, local_mem_size, i - 1, j + 1) +
           getPixel2(okolica, local_mem_size, i + 1, j - 1) +
           2 * getPixel2(okolica, local_mem_size, i + 1, j) +
           getPixel2(okolica, local_mem_size, i + 1, j + 1);
		Gy = -getPixel2(okolica, local_mem_size, i - 1, j - 1) -
           2 * getPixel2(okolica, local_mem_size, i, j - 1) -
           getPixel2(okolica, local_mem_size, i + 1, j - 1) +
           getPixel2(okolica, local_mem_size, i - 1, j + 1) +
           2 * getPixel2(okolica, local_mem_size, i, j + 1) +
           getPixel2(okolica, local_mem_size, i + 1, j + 1);
		
		tempPixel = sqrt((float)(Gx * Gx + Gy * Gy));
		if (tempPixel > 255)
			imageOut[gy * width + gx] = 255;
		else
			imageOut[gy * width + gx] = tempPixel;
	}

}
