#include "helpers.hpp"
#include <string>

std::string toHex(unsigned int value, int len) {
  char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
  char hex[len+1];
  hex[len] = '\0';

  for (int i = len-1; i >= 0; i--) {
    if (value > 0) {
      hex[i] = hexChars[value % 16];
      value /= 16;
    } else {
      hex[i] = '0';
    }
  }

  return std::string(hex);
}
