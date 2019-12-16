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

inline float getValue(__global float *matrix, int width, int height, int y, int x) {
    if (x >= width || y >= height)
        return 0;
    return matrix[y * width + x];
}

// inline void setValue(__global float *matrix, int width, int height, int y, int x, float value) {
//     if (x >= width || y >= height)
//         return;
//     matrix[y * width + x] = value;
// }

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

    // if (gx < widthA && matrixB_start < heightB) {}

    // move vertically over A
    for (int i = ly; i < heightA; i += WORKGROUP_SIZE) {
        As[ly][lx] = getValue(A, widthA, heightA, i, gx);
        // As[ly][lx] = A[i * widthA + gx];

        // move horizontally over B
        for (int j = lx; j < widthB; j += WORKGROUP_SIZE) {
            // Bs[ly][lx] = getValue(B, widthB, heightB, matrixB_y, j);
            Bs[ly][lx] = B[matrixB_y * widthB + j];

            barrier(CLK_LOCAL_MEM_FENCE);

            // if (gx < widthA && matrixB_y < heightB) {
                // if (groupId == 1 && lx == 0 && ly == 0) {
                //     printf("A:\n");
                //     for (int s1 = 0; s1 < WORKGROUP_SIZE; s1++) {
                //         for (int s2 = 0; s2 < WORKGROUP_SIZE; s2++) {
                //             printf("%f, ", As[s1][s2]);
                //         }
                //         printf("\n");
                //     }
                //     printf("B:\n");
                //     for (int s1 = 0; s1 < WORKGROUP_SIZE; s1++) {
                //         for (int s2 = 0; s2 < WORKGROUP_SIZE; s2++) {
                //             printf("%f, ", Bs[s1][s2]);
                //         }
                //         printf("\n");
                //     }
                // }

                float sum = 0;
                for (int k = 0; k < WORKGROUP_SIZE; k++) {
                    sum += As[ly][k] * Bs[k][lx];

                    if (groupId == 0 && lx == 1 && ly == 0) {
                        printf("XXX: %d %f %f %f\n", matrixB_y, As[ly][k], Bs[k][lx], sum);
                    }
                }

                // write to global memory C
                C[matrixC_start + i * widthC + j] = sum;
                // setValue(C, widthC, heightA)
                // C[matrixC_start + i * widthC + j] = sum;

            // }

            barrier(CLK_LOCAL_MEM_FENCE);
        }
    }

    if (groupId == 0 && lx == 1 && ly == 0) {
        printf("------------------------------C:\n");
        for (int s1 = 0; s1 < heightA; s1++) {
            for (int s2 = 0; s2 < widthB; s2++) {
                printf("%f, ", C[matrixC_start + s1 * widthC + s2]);
            }
            printf("\n");
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

    if (gy < heightC && gx < widthX) {
        for (int i = gx; i < widthC; i += widthX) {
            sum += C[gy * widthC + i];
            // if (gx == 0) {
            //     printf("SUMC %d %f\n", gy, sum);
            // }
        }

        X[gy * widthX + gx] = sum;
    }
}
