//const __local char lokalni[width*width];

inline int getPixelGlobal(__global unsigned char *image, int width, int height, int y, int x)
{
    if (x < 0 || x >= width)
        return 0;
    if (y < 0 || y >= height)
        return 0;
    return image[y*width + x];
}

inline int getPixelLocal(__local unsigned char *image, int width, int height, int y, int x)
{
    x+=1;
    y+=1;

    if (x < 0 || x >= width)
        return 0;
    if (y < 0 || y >= height)
        return 0;
    return image[y*width + x];
}

__kernel void dotProduct(__global unsigned char *imageIn, __global unsigned char *imageOut, int width, int height)                     
{       

    __local unsigned char lokalni[34*3];
    int localWidth = 34;

    int gidX = get_global_id(0); 
    int gidY = get_global_id(1);    

    int lidX = get_local_id(0);
    int lidY = get_local_id(1);

    /*if(lidY == 0 && lidX == 0)
        for(int i=0;i<width;i++)
            for(int j=0;j<width;j++)
                lokalni[i + width * j] = imageIn[gidX+i + width * (gidY+j)];*/

    int Gx, Gy;
    int tempPixel;  

    //za vsak piksel v sliki
    if(gidY < height)
        if(gidX < width)
        {
            lokalni[(lidY+0)*localWidth + lidX+1] = getPixelGlobal(imageIn, width, height, gidY-1, gidX); 
            lokalni[(lidY+1)*localWidth + lidX+1] = getPixelGlobal(imageIn, width, height, gidY, gidX);
            lokalni[(lidY+2)*localWidth + lidX+1] = getPixelGlobal(imageIn, width, height, gidY+1, gidX);

            if(lidY == 0 && lidX == 0)
                for(int i=0;i<3;i++)
                    lokalni[(lidY+i)*localWidth + 0] = getPixelGlobal(imageIn, width, height, gidY-1+i, gidX-1); 

            if(lidY == 0 && lidX == 31)
                for(int i=0;i<3;i++)
                    lokalni[(lidY+i)*localWidth + localWidth-1] = getPixelGlobal(imageIn, width, height, gidY-1+i, gidX+1); 

            barrier(CLK_LOCAL_MEM_FENCE);
            printf("gidY:%d, gidX:%d, lidY:%d, lidX:%d, valGlobalni: %d,  valLokalni:%d\n",gidY,gidX,lidY,lidX, imageIn[gidY*width + gidX], lokalni[(lidY+1)*localWidth + lidX+1]);

            Gx =   -getPixelLocal(lokalni, localWidth, 3, lidY - 1, lidX - 1)-2* getPixelLocal(lokalni, localWidth, 3, lidY - 1, lidX) -
                    getPixelLocal(lokalni, localWidth, 3, lidY - 1, lidX + 1)  + getPixelLocal(lokalni, localWidth, 3, lidY + 1, lidX - 1) +
                2 * getPixelLocal(lokalni, localWidth, 3, lidY + 1, lidX)      + getPixelLocal(lokalni, localWidth, 3, lidY + 1, lidX + 1);
                
            Gy =   -getPixelLocal(lokalni, localWidth, 3, lidY - 1, lidX - 1)-2* getPixelLocal(lokalni, localWidth, 3, lidY, lidX - 1) -
                    getPixelLocal(lokalni, localWidth, 3, lidY + 1, lidX - 1)  + getPixelLocal(lokalni, localWidth, 3, lidY - 1, lidX + 1) +
                2 * getPixelLocal(lokalni, localWidth, 3, lidY, lidX + 1)      + getPixelLocal(lokalni, localWidth, 3, lidY + 1, lidX + 1);

            tempPixel = sqrt((float)(Gx * Gx + Gy * Gy));
            if (tempPixel > 255)
                imageOut[gidY*width + gidX] = 255;
            else
                imageOut[gidY*width + gidX] = tempPixel;
            //imageOut[gidY*width + gidX] = 255;
        }
    //printf("seenkat gidY:%d, gidX:%d, lidY:%d, lidX:%d, valGlobalni: %d,  valLokalni:%d\n",gidY,gidX,lidY,lidX, imageIn[gidY*width + gidX], lokalni[lidY*width + lidX]);
    //imageOut[gidY*width + gidX] = 255;
    
} 

/*
inline int getPixel(__global unsigned char *image, int width, int height, int y, int x)
{
    if (x < 0 || x >= width)
        return 0;
    if (y < 0 || y >= height)
        return 0;
    return image[y*width + x];
}

__kernel void dotProduct(__global unsigned char *imageIn, __global unsigned char *imageOut, int width, int height)                     
{       

    //__local unsigned char lokalni[width*width];

    int gidX = get_global_id(0); 
    int gidY = get_global_id(1);    

    int lidX = get_local_id(0);
    int lidY = get_local_id(1);

    //lokalni[lidY*width + lidX] = imageIn[gidX + width * gidY];      
    //barrier(CLK_LOCAL_MEM_FENCE);
    //printf("gidY:%d, gidX:%d, lidY:%d, lidX:%d, valGlobalni: %d,  valLokalni:%d\n",gidY,gidX,lidY,lidX, imageIn[gidY*width + gidX], lokalni[lidY*width + lidX]);

    int Gx, Gy;
    int tempPixel;

    

    //za vsak piksel v sliki
    if(gidY < height)
        if(gidX < width)
        {
            Gx =   -getPixel(imageIn, width, height, gidY - 1, gidX - 1)-2* getPixel(imageIn, width, height, gidY - 1, gidX) -
                    getPixel(imageIn, width, height, gidY - 1, gidX + 1)  + getPixel(imageIn, width, height, gidY + 1, gidX - 1) +
                2 * getPixel(imageIn, width, height, gidY + 1, gidX)      + getPixel(imageIn, width, height, gidY + 1, gidX + 1);
                
            Gy =   -getPixel(imageIn, width, height, gidY - 1, gidX - 1)-2* getPixel(imageIn, width, height, gidY, gidX - 1) -
                    getPixel(imageIn, width, height, gidY + 1, gidX - 1)  + getPixel(imageIn, width, height, gidY - 1, gidX + 1) +
                2 * getPixel(imageIn, width, height, gidY, gidX + 1)      + getPixel(imageIn, width, height, gidY + 1, gidX + 1);

            tempPixel = sqrt((float)(Gx * Gx + Gy * Gy));
            if (tempPixel > 255)
                imageOut[gidY*width + gidX] = 255;
            else
                imageOut[gidY*width + gidX] = tempPixel;
            //imageOut[gidY*width + gidX] = 255;
        }
    //printf("seenkat gidY:%d, gidX:%d, lidY:%d, lidX:%d, valGlobalni: %d,  valLokalni:%d\n",gidY,gidX,lidY,lidX, imageIn[gidY*width + gidX], lokalni[lidY*width + lidX]);
    //imageOut[gidY*width + gidX] = 255;
    
} 
*/