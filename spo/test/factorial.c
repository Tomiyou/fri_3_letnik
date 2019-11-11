#include <stdio.h>

int factorial(int x);

int main() {
  printf("Factorial: %d\n", factorial(5));
  return 0;
}

int factorial(int x) {
  if (x == 1) {
    return 1;
  }

  return x * factorial(x-1);
}