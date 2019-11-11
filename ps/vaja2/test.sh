echo "Testing Pthread"
sudo perf stat -r 30 -d ./prij_pthread
echo "\n\nTesting OpenMP"
sudo perf stat -r 30 -d ./prij_openmp