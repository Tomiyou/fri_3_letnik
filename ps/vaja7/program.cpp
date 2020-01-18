#include <cmath>
#include <iostream>
#include <mpi.h>
#include <queue>
#include <stdlib.h>
#include <sys/time.h>

#define N 13873789
// #define N 25787898

void swap(int *a, int *b) {
  int t = *a;
  *a = *b;
  *b = t;
}

int partition(int arr[], int low, int high) {
  int pivot = arr[high];
  int i = (low - 1);

  for (int j = low; j <= high - 1; j++) {
    if (arr[j] < pivot) {
      i++;
      swap(&arr[i], &arr[j]);
    }
  }
  swap(&arr[i + 1], &arr[high]);
  return (i + 1);
}

void quickSort(int arr[], int low, int high) {
  if (low < high) {
    int pi = partition(arr, low, high);

    quickSort(arr, low, pi - 1);
    quickSort(arr, pi + 1, high);
  }
}

void printArr(int *arr, int length) {
  int i;
  for (i = 0, --length; i < length; i++) {
    std::cout << arr[i] << ", ";
  }
  std::cout << arr[i] << "\n";
}

unsigned long long getTime() {
  struct timeval tv;

  gettimeofday(&tv, NULL);

  return (unsigned long long)(tv.tv_sec) * 1000 +
         (unsigned long long)(tv.tv_usec) / 1000;
}

int main(int argc, char **argv) {
  int rank, size;
  int *items1 = (int *)malloc(sizeof(int) * N);
  int *items2 = (int *)malloc(sizeof(int) * N);

  for (int i = 0; i < N; i++) {
    int num = rand();
    items1[i] = num;
    items2[i] = num;
  }

  auto time1 = getTime();
  quickSort(items2, 0, N - 1);
  auto time2 = getTime();

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  if (rank == 0)
    std::cout << "Elapsed CPU sort: " << (time2 - time1) << '\n';

  int itemsPerWorker = N / size;
  int sendcounts[size], displs[size], acc = N - size * itemsPerWorker;
  for (int i = 0; i < size; i++)
    sendcounts[i] = itemsPerWorker;

  for (; acc > 0; acc--)
    sendcounts[acc] += 1;

  for (int i = 0; i < size; i++) {
    displs[i] = acc;
    acc += sendcounts[i];
  }

  if (rank == 0) {
    std::cout << "Counts: \n";
    printArr(sendcounts, size);
    std::cout << "Displs: \n";
    printArr(displs, size);
  }

  // begin GPU time
  auto time3 = getTime();
  int *data = (int *)malloc(sizeof(int) * sendcounts[rank]);
  MPI_Scatterv(items1, sendcounts, displs, MPI_INT, data, sendcounts[rank],
               MPI_INT, 0, MPI_COMM_WORLD);

  quickSort(data, 0, sendcounts[rank] - 1);

  MPI_Gatherv(data, sendcounts[rank], MPI_INT, items1, sendcounts, displs,
              MPI_INT, 0, MPI_COMM_WORLD);

  if (rank == 0) {
    // std::cout << "Gathered :\n";
    // printArr(items1, N);

    for (int l = 0; l < N; l++) {
      int lowestFragment = -1;

      for (int i = 0; i < size; i++) {
        if (sendcounts[i] == 0)
          continue;

        if (lowestFragment == -1)
          lowestFragment = i;
        else if (items1[displs[lowestFragment]] > items1[displs[i]]) {
          lowestFragment = i;
        }
      }

      // std::cout << "Item: " << items1[displs[lowestFragment]] << '\n';
      if (items1[displs[lowestFragment]] != items2[l])
        printf("ERROR: (%d) %d != %d\n", l, items1[displs[lowestFragment]], items2[l]);

      displs[lowestFragment] += 1;
      sendcounts[lowestFragment] -= 1;
    }

    auto time4 = getTime();
    std::cout << "Elapsed GPU: " << time4 - time3 << '\n';

    // std::cout << "Sorted :\n";
    // printArr(items1, N);

    // for (int i = 0; i < N; i++)
    //   if (items1[i] != items2[i])
    //     printf("ERROR: (%d) %d != %d\n", i, items1[i], items2[i]);
  }

  free(items1);
  free(items2);
  free(data);

  MPI_Finalize();
}