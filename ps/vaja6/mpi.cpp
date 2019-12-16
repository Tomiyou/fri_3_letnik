#include <mpi.h>
#include <stdio.h>
#include <string>
#include <iostream>
#include <sstream>

std::string receive() {
  char str[50];
  MPI_Status status;
  MPI_Recv(&str, 50, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);

  std::string s(str);
  return s;
}

bool send(int destination, std::string data) {
  MPI_Send(data.c_str(), data.size() + 1, MPI_CHAR, destination, 0, MPI_COMM_WORLD);

  return true;
}

int main(int argc, char** argv) {
  // Initialize the MPI environment
  MPI_Init(&argc, &argv);

  // Get the rank of the process
  int my_rank;
  MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);

  if (my_rank == 0) {
    send(0, "0");
    std::string result = receive();
    std::cout << result << " - 0\n";
  } else {
    std::string data = receive();
    std::stringstream sstr(data);
    sstr << " - " << my_rank;
    send(my_rank + 1, sstr.str());
  }

  // Finalize the MPI environment.
  MPI_Finalize();
}