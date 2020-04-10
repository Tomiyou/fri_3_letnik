#define WORKGROUP_SIZE	8
  
__kernel void matrixMultiply(__global float *A,
							 __global float *B,
							 __global float *C,
							 int heightA,
							 int widthA,
							 int heightB,
							 int widthB)
{
    // indeks bloka
    int bx = get_group_id(0);
    int by = get_group_id(1);
 
    // indeks niti
    int tx = get_local_id(0);
    int ty = get_local_id(1);
 
    // indeks elementa iz A, ki ozna"cuje za"cetek vrstice v matriki
    int aBegin = widthA * WORKGROUP_SIZE * by; 
    // indeks elementa iz A, ki ozna"cuje konec(+1) vrstice v matriki
    int aEnd   = aBegin + widthA;
	// korak v A do naslednjega bloka
    int aStep  = WORKGROUP_SIZE;
    // indeks prvega elementa iz B, ki je v bloku
    int bBegin = WORKGROUP_SIZE * bx;
    // korak v B do nasledenjga bloka
    int bStep  = WORKGROUP_SIZE * widthB;

    // alociramo lokalni pomnilnik
    __local float As[WORKGROUP_SIZE][WORKGROUP_SIZE];
    __local float Bs[WORKGROUP_SIZE][WORKGROUP_SIZE];
 
	float Csub = 0.0f;
    // gremo "cez vse podmatrike A in B
    for (int a = aBegin, b = bBegin; a < aEnd; a += aStep, b += bStep) 
    {
		// vsaka nit vpi"se v lokalni pomnilnik dva podatka
        As[ty][tx] = A[a + widthA * ty + tx];
        Bs[ty][tx] = B[b + widthB * ty + tx];
 
        // po"cakamo, da vse niti zaklju"cijo
        barrier(CLK_LOCAL_MEM_FENCE);
 
		// zmno"zimo
        for (int k = 0; k < WORKGROUP_SIZE; ++k)
            Csub += As[ty][k] * Bs[k][tx];
		
		// po"cakamo, da vse niti zaklju"cijo
        barrier(CLK_LOCAL_MEM_FENCE); 
    }

	// indeks prvega elementa v bloku
    int c = widthB * WORKGROUP_SIZE * by + WORKGROUP_SIZE * bx;

	// vpi"semo vrednost
    C[c + widthB * ty + tx] = Csub;
}

// ------------------------------------------------------------------------------------------

int ceilDivide(int a, int b) {
  return (a + b - 1) / b;
}

__kernel void longMultiply(__global float *A,
                           __global float *B,
                           __global float *C,
                           int heightA,
                           int widthA,
                           int heightB,
                           int widthB)
{
    int groupCount = get_num_groups(1);
    int groupId = get_group_id(1);
    int widthC = widthB * groupCount;

    // lokalni indeks niti
    int ly = get_local_id(0);
    int lx = get_local_id(1);
 
    // globalni indeks niti
    int gy = get_global_id(0);
    int gx = get_global_id(1);

    // alociramo lokalni pomnilnik
    __local float As[WORKGROUP_SIZE][WORKGROUP_SIZE];
    __local float Bs[WORKGROUP_SIZE][WORKGROUP_SIZE];

    int matrixB_y = groupId * WORKGROUP_SIZE + ly;
    int matrixC_start = groupId * widthB;

    // move vertically over A
    for (int i = 0; i < ceilDivide(heightA, WORKGROUP_SIZE); i++) {
        if (i * WORKGROUP_SIZE + ly < heightA && gx < widthA) {
            As[ly][lx] = A[(i * WORKGROUP_SIZE + ly) * widthA + gx];
        } else {
            As[ly][lx] = 0;
        }

        for (int j = 0; j < ceilDivide(widthB, WORKGROUP_SIZE); j++) {
            if (j * WORKGROUP_SIZE + lx < widthB && matrixB_y < heightB) {
                Bs[ly][lx] = B[matrixB_y * widthB + (j * WORKGROUP_SIZE + lx)];
            } else {
                Bs[ly][lx] = 0;
            }

            barrier(CLK_LOCAL_MEM_FENCE);

            float sum = 0;
            for (int k = 0; k < WORKGROUP_SIZE; k++) {
                if (gy == 0 && gx == 0) {
                    printf("%f:%f, ", localA[ly][k], localB[k][lx]);
                }
                
                sum += As[ly][k] * Bs[k][lx];
            }

            printf('\n');

            if (i * WORKGROUP_SIZE + ly < heightA && j * WORKGROUP_SIZE + lx < widthB) {
                C[matrixC_start + (i * WORKGROUP_SIZE + ly) * widthC + (j * WORKGROUP_SIZE + lx)] = sum;
            }

            barrier(CLK_LOCAL_MEM_FENCE);
        }
    }
}

__kernel void sumMatrices(__global float *C,
                          __global float *X,
                          int heightC,
                          int widthC,
                          int heightX,
                          int widthX)
{
    int threadCount = get_global_size(1);
    
    // lokalni indeks niti
    int lx = get_local_id(1);
    int ly = get_local_id(0);
 
    // globalni indeks niti
    int gx = get_global_id(1);
    int gy = get_global_id(0);

    // alociramo lokalni pomnilnik
    float sum = 0;

    if (gy < heightC && gx < widthB) {
        for (int i = gx; i < widthC; i += widthB) {
            sum += C[gy * widthC + i];
        }

        X[gy * widthB + gx] = sum;
    }
}
