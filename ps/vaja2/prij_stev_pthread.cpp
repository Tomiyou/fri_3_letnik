#include <pthread.h>
#include <stdio.h>
#include <math.h>
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
void* staticno_thread(void *arg);
void razrezano(int *vsote);
void* razrezano_thread(void *arg);
void dinamicno(int *vsote);
void* dinamicno_thread(void *arg);

pthread_mutex_t lock;

int main() {
  pthread_mutex_init(&lock, NULL);

  int *vsote1 = (int *)malloc(sizeof(int) * N);

  staticno(vsote1);

  // if (!compare(vsote1, vsote2)) printf("DELITELJI ERROR\n");
  // else {
    printSorodna(vsote1);
  // }

  free(vsote1);

  pthread_mutex_destroy(&lock);
}

void sequential(int *vsote) {
  for (int i = 1; i <= N; i++) {
    vsote[i] = vsotaDeliteljev(i);
  }
}

void staticno(int *vsote) {
  pthread_t threads[maxThreads];
  for (int i = 0; i < maxThreads; i++) {
    thread_args *args = (thread_args *)malloc(sizeof(thread_args));
    args->next = i;
    args->results = vsote;
    pthread_create(&threads[i], NULL, staticno_thread, (void *)args);
  }

  for (int i = 0; i < maxThreads; i++) {
    pthread_join(threads[i], NULL);
  }
}

void* staticno_thread(void *arg) {
  // printf("Staticno thread.\n");
  thread_args *pointers = (thread_args *)arg;
  int next = pointers->next;
  int *results = pointers->results;
  free(arg);

  // prevzemi naslednji batch
  for (;next <= N; next += maxThreads) {
    results[next] = vsotaDeliteljev(next);
  }

  return NULL;
}

void razrezano(int *vsote) {
  pthread_t threads[maxThreads];
  for (int i = 0; i < maxThreads; i++) {
    thread_args *args = (thread_args *)malloc(sizeof(thread_args));
    args->next = (N/maxThreads + 1) * i;
    args->results = vsote;
    pthread_create(&threads[i], NULL, razrezano_thread, (void *)args);
  }

  for (int i = 0; i < maxThreads; i++) {
    pthread_join(threads[i], NULL);
  }
}

void* razrezano_thread(void *arg) {
  // printf("Razrezano thread.\n");
  thread_args *pointers = (thread_args *)arg;
  int next = pointers->next;
  int *results = pointers->results;
  free(arg);

  for (int i = 0; i < N/maxThreads + 1 && next + i <= N; i++) {
    results[next + i] = vsotaDeliteljev(next + i);
  }

  return NULL;
}

void dinamicno(int *vsote) {
  thread_args args{ 1, vsote };
  
  pthread_t threads[maxThreads];
  for (int i = 0; i < maxThreads; i++) {
    pthread_create(&threads[i], NULL, dinamicno_thread, (void*)&args);
  }

  for (int i = 0; i < maxThreads; i++) {
    pthread_join(threads[i], NULL);
  }
}

void* dinamicno_thread(void *arg) {
  // printf("Dinamicno thread.\n");
  thread_args *args = (thread_args *)arg;
  int *results = args->results, next;
  int me = -1;

  // prevzemi naslednji batch
  while (1) {
    pthread_mutex_lock(&lock);
    next = args->next;
    if (next >= N) {
      pthread_mutex_unlock(&lock);
      break;
    }

    args->next += dynamic_batch_size;
    pthread_mutex_unlock(&lock);
    
    int end = next + min(dynamic_batch_size, N - next + 1);
    // printf("Going in thread: %d, next: %d, len: %d\n", me, next, end);
    for (; next < end; next++) {
      results[next] = vsotaDeliteljev(next);
    }
  }

  return NULL;
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
