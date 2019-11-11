echo "Compiling Pthread version"
gcc prij_stev_pthread.cpp -o prij_pthread -lpthread -lm -O3

echo "Compiling OpenMP version"
gcc prij_stev_openmp.c -o prij_openmp -fopenmp -lm -O3