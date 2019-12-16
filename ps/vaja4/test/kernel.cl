// kernel
int getPixel(__global unsigned char *image, int width, int height, int y, int x)
{
	if (x < 0 || x >= width)
		return 0;
	if (y < 0 || y >= height)
		return 0;
	return image[y*width + x];
}
int getPixelT(__local unsigned char table[18][18], int width, int height, int y, int x)
{
	return table[x+1][y+1];
}


__kernel void sobel(__global unsigned char *imageIn,
	__global unsigned char *imageOut,
	int width,
	int height,
	int wkSize)
{

	__local unsigned char table[10][10];
	int startI = 1;
	int startJ = 1;
	//i je y j je x
	int i, j;
	j = get_global_id(0);
	i = get_global_id(1);
	imageOut[i*width + j] = 155;

// 	int jL = get_local_id(0);
// 	int iL = get_local_id(1);

// 	int sizeI = get_local_size(0);
// 	int sizeJ = get_local_size(1);
// 	//pixel na i,j
// 	table[jL+startJ][iL+startI]=getPixel(imageIn,width,height,i,j);
	
// 	//ce nit na robu in v kotu 
// 	if(jL==0){
// 		table[0][iL+startI] = getPixel(imageIn,width,height,i,j-1);
// 	}
// 	if(jL==sizeJ-1){
// 		table[9][iL+startI] = getPixel(imageIn,width,height,i,j+1);
// 	}
// 	if(iL==0){
// 		table[jL+startJ][0] = getPixel(imageIn,width,height,i-1,j);
// 	}
// 	if(iL==sizeI-1){
// 		table[jL+startJ][9] = getPixel(imageIn,width,height,i+1,j);
// 	}

// 	if(iL==0 && jL==0){
// 		table[0][0] = getPixel(imageIn,width,height,i-1,j-1);
// 	}
// 	if(iL==sizeI-1 && jL==sizeJ-1){
// 		table[9][9] = getPixel(imageIn,width,height,i+1,j+1);
// 	}
// 	if(iL==0 && jL==sizeJ-1){
// 		table[9][0] = getPixel(imageIn,width,height,i-1,j+1);
// 	}
// 	if(iL==sizeI-1 && jL==0){
// 		table[0][9] = getPixel(imageIn,width,height,i+1,j-1);
// 	}


// 	barrier(CLK_LOCAL_MEM_FENCE);

// 	int Gx, Gy;
// 	int tempPixel;
// 	//za vsak piksel v sliki
// 	iL=iL+1;
// 	jL=jL+1;
// 			Gx = -table[jL-1][iL-1]-2*table[jL][iL-1]-
// 				table[jL+1][iL-1] + table[jL-1][iL+1]+
// 				2*table[jL][iL+1] + table[jL+1][iL+1];
				
// 			/*Gx = -getPixelT(table, width, height, iL - 1, jL - 1) - 2 * getPixelT(table, width, height, iL - 1, jL) -
// 				getPixelT(table, width, height, iL - 1, jL + 1) + getPixelT(table, width, height, iL + 1, jL - 1) +
// 				2 * getPixelT(table, width, height, iL + 1, jL) + getPixelT(table, width, height, iL + 1, jL + 1);
// 				*/
			
// 			Gy = -table[jL-1][iL-1]-2*table[jL-1][iL]-
// 				table[jL-1][iL+1]+table[jL+1][iL-1]+
// 				2*table[jL+1][iL]+table[jL+1][iL+1];
// 			/*Gy = -getPixelT(table, width, height, iL - 1, jL - 1) - 2 * getPixelT(table, width, height, iL, jL - 1) -
// 				getPixelT(table, width, height, iL + 1, jL - 1) + getPixelT(table, width, height, iL - 1, jL + 1) +
// 				2 * getPixelT(table, width, height, iL, jL + 1) + getPixelT(table, width, height, iL + 1, jL + 1);
// 				*/
			
// 			tempPixel = sqrt((float)(Gx * Gx + Gy * Gy));
// 			if (tempPixel > 255)
// 				imageOut[i*width + j] = 255;
// 			else
// 				imageOut[i*width + j] = tempPixel;


// 	// if (j < 27 || j > 99 || i == 635) {
// 	// 	imageOut[i*width + j] = 255;
// 	// }
}
