#include <CL/sycl.hpp>
#include <array>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

#define WORKGROUP_SIZE 8
#define EPSILON 0.1
#define N 4096

std::array<int, N> gpuSort(std::array<int, N> &numbers);
void compare(std::array<int, N> &numbers_cpu, std::array<int, N> &numbers_gpu) {
  for (int i = 0; i < N; i++)
    if (numbers_cpu[i] != numbers_gpu[i])
      std::cout << "Error: " << numbers_cpu[i] << " : " << numbers_gpu[i] << '\n';
}

int main() {
  srand((int)time(NULL));

  std::array<int, N> numbers_cpu;
  std::array<int, N> numbers_gpu;
  for (int i = 0; i < N; i++) {
    int num = rand() % 1000;
    numbers_cpu[i] = num;
    numbers_gpu[i] = num;
  }

  std::sort(numbers_cpu.begin(), numbers_cpu.end());
  numbers_gpu = gpuSort(numbers_gpu);

  std::cout << "Errors:\n";
  compare(numbers_cpu, numbers_gpu);

  return 0;
}


std::array<int, N> gpuSort(std::array<int, N> &numbers) {
  namespace sycl = cl::sycl;

  std::array<int, N> output_numbers;

  // izberemo napravo
  sycl::default_selector device_selector;

  // za izbrano napravo ustvarimo vrsto ščepcev
  sycl::queue device_queue(device_selector);

  std::cout << "Device: "
            << device_queue.get_device().get_info<sycl::info::device::name>()
            << std::endl;

  // SYCL uporablja statično tipizirane dimenzije
  sycl::range<1> data_size{N};
  sycl::range<1> local_size{WORKGROUP_SIZE};

  // ustvarimo buffer tipa int, 1 dimenzije ranga data_size, ter tja kopiramo številke
  sycl::buffer<int, 1> data(numbers.data(), data_size);
  // ustvarimo buffer tipa int, 1 dimenzije ranga data_size, sem bomo zapisali izhod
  sycl::buffer<int, 1> output(output_numbers.data(), data_size);

  // ščepec damo v vrsto
  device_queue.submit([&](sycl::handler &cgh) {
    // ustvarimo dostopnik za vhodne podatke, preko teh dostopnikov oneApi zna sam
    // ustvariti drevo odvisnosti
    auto data_acc = data.get_access<sycl::access::mode::read_write>(cgh);
    // ustvarimo dostopnik za izhodne podatke
    auto output_acc = output.get_access<sycl::access::mode::write>(cgh);

    // ustvarimo dostopnik za lokalni pomnilnik delovne skupine, ta je bolj ekspliciten
    // kot dostopnik za buffer, ker se pri prejšnjem lahko implicitno razbere določene
    // lastnosti
    // ustvarimo dostopnik tipa int, 1 dimenzije, read_write access, na lokalnem pomnilniku
    sycl::accessor<int, 1, sycl::access::mode::read_write, sycl::access::target::local> local(
        local_size, cgh);

    // stream za izpis in razhroščevanje, podamo velikost stream bufferja ter največje
    // število bajtov, ki jih lahk z enim samim stavkom zapišemo v stream
    sycl::stream out(1024, 256, cgh);

    // poženemo lambda funkcijo, podamo celotno velikost in velikost delovne skupine
    cgh.parallel_for<class SelectionSort>(
        sycl::nd_range<1>(data_size, local_size), [=](sycl::nd_item<1> item) {
          int g_id = item.get_global_id(0); // globalni id niti
          int l_id = item.get_local_id(0);  // lokalni id niti
          int n = item.get_global_range(0); // število vseh niti
          int wg = item.get_local_range(0); // velikost delovne skupine

          int me = data_acc[g_id];
          int position = 0;

          if (g_id == 0) {
            // v stream pišemo kot v navaden stream v C++
            out << "GLOBAL: " << n << ", LOCAL: " << wg << '\n';
          }

          // vsaka nit prešteje koliko elementov je manjših od svojega
          for (int block_start = 0; block_start < n; block_start += wg) {
            // najprej vsaka nit skopira 1 element iz globalnega v lokalni pomnilnik
            local[l_id] = data_acc[block_start + l_id];

            for (int index = 0; index < wg; index++) {
              int other = local[index];
              bool smaller =
                  (other < me) || (other == me && (block_start + index) < g_id);
              position += smaller;
            }

            // sinhroniziramo delovno skupino preko lokalnega pomnilnika
            item.barrier(sycl::access::fence_space::local_space);
          }

          // vsaka nit zapiše svoj element na pravo mesto
          output_acc[position] = me;
        });
  });

  return output_numbers;
}