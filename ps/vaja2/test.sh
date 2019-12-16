echo "Testing Pthread"
sudo perf stat -r 30 -d ./prij_pthread
echo "\n"
echo "Testing OpenMP"
sudo perf stat -r 10 -d ./prij_openmp