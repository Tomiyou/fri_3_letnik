#include <stdlib.h>
#include <pthread.h>
#include <stdio.h>
#include <math.h>
#include <omp.h>
#include "consts.h"

typedef struct thread_args {
  int next, *results;
} thread_args;

int compare(int *vsote1, int *vsote2);
void printSorodna(int *vsote);
int vsotaDeliteljev(int x);
int min(int a, int b);

void sequential(int *vsote);
void staticno(int *vsote);
void razrezano(int *vsote);
void dinamicno(int *vsote);

int main() {

  int *vsote1 = (int *)malloc(sizeof(int) * N);

  dinamicno(vsote1);

  // if (!compare(vsote1, vsote2)) printf("DELITELJI ERROR\n");
  // else {
    // printSorodna(vsote1);
  // }

  free(vsote1);
}

void sequential(int *vsote) {
  for (int i = 1; i <= N; i++) {
    vsote[i] = vsotaDeliteljev(i);
  }
}

void staticno(int *vsote) {
  #pragma omp parallel
  for (int i = omp_get_thread_num(); i <= N; i += maxThreads) {
    vsote[i] = vsotaDeliteljev(i);
  }
}

void razrezano(int *vsote) {
  int slice_size = N/maxThreads + 1;

  #pragma omp parallel
  for (int i = 0, next = slice_size * omp_get_thread_num(); i < slice_size && next + i <= N; i++) {
    vsote[next + i] = vsotaDeliteljev(next + i);
  }
}

void dinamicno(int *vsote) {
  int next_in_line = 1;

  #pragma omp parallel
  for (int next = 0; 1;) {
    #pragma omp critical
    {
      next = next_in_line;
      next_in_line += dynamic_batch_size;
    }
    
    if (next >= N) {
      break;
    }
    
    int end = next + min(dynamic_batch_size, N - next + 1);
    // printf("Going in thread: %d, next: %d, len: %d\n", omp_get_thread_num(), next, end);
    for (; next < end; next++) {
      vsote[next] = vsotaDeliteljev(next);
    }
  }
}

int vsotaDeliteljev(int x) {
  int koren = sqrt((int) x);
  int vsota = 0;

  for (int i = 2; i <= koren; i++) {
    if (x % i == 0) {
      // i je delitelj x
      if (i == x/i)
        vsota += i;
      else
        vsota += i + x/i;
    }
  }

  return vsota + 1;
}

int min(int a, int b) {
  if (a < b)
    return a;
  else
    return b;
}

int compare(int *vsote1, int *vsote2) {
  for (int i = 1; i <= N; i++)
    if (vsote1[i] != vsote2[i]) {
      printf("Napaka (%d): %d %d\n", i, vsote1[i], vsote2[i]);
      return 0;
    }
  
  return 1;
}

void printSorodna(int *vsote) {
  int vd;
  for (int i = 1; i <= N; i++) {
    vd = vsote[i];

    if (vd > N) continue;

    if (i < vd && i == vsote[vd]) {
      printf("Sorodni stevili: %d %d\n", i, vd);
    }
  }
}
