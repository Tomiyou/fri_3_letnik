#include "emulator.hpp"
#include <sstream>
#include <bitset>

BYTE Emulator::fetch() {
  BYTE val = getByte(PC);
  std::cout << "PC: " << std::bitset<32>(PC) << ", val: " << std::bitset<8>(val) << "\n";
  PC += 1;
  return val;
}

bool Emulator::execute() {
  halt = false;
  
  BYTE opcode = fetch();
  std::cout << "Opcode: " << std::bitset<8>(opcode) << "\n";
  
  if (execF1(opcode))
    return halt;

  WORD operand = fetch();
  if (execF2(opcode, operand))
    return halt;

  operand = operand << 8 | fetch();
  BYTE ni = opcode % 4;
  if (execSICF3F4(opcode & ~(3), ni, operand))
    return halt;

  std::stringstream ss;
  unsigned int aa = opcode;
  ss << "Error: unknown instruction: " << std::hex << aa << "\n";
  std::string ouput = ss.str();
  std::cout << ouput;
  throw ouput.c_str();
}