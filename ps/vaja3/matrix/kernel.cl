// kernel
__kernel void matrixAdd(__global const float *A,
						 __global const float *B,		
						 __global float *C,				
						 int width,
						 int height)						
{														
	int y = get_global_id(0);
	int x = get_global_id(1);

	C[width*y+x] = A[width*y+x] + B[width*y+x];
}