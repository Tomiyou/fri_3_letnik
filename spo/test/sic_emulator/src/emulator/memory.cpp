#include "emulator.hpp"

int Emulator::getMemoryViewAddress() {
  int ret = memoryChanged ? 0 : -1;
  memoryChanged = false;
  return ret;
}

WORD Emulator::getByte(WORD addr) {
  if (addr > MAX_ADDRESS)
    throw "Error: Address too large";
  return memory[addr];
}
void Emulator::setByte(WORD addr, WORD val) {
  if (addr > MAX_ADDRESS)
    throw "Error: Address too large";
  memory[addr] = (BYTE)val;
  memoryChanged = true;
}
WORD Emulator::getWord(WORD addr) {
  if (addr + 2 > MAX_ADDRESS)
    throw "Error: Address too large";
  return getByte(addr) << 16 | getByte(addr + 1) << 8 | getByte(addr + 2);
}
void Emulator::setWord(WORD addr, WORD val) {
  if (addr + 2 > MAX_ADDRESS)
    throw "Error: Address too large";
  setByte(addr, val >> 16);
  setByte(addr + 1, val >> 8);
  setByte(addr + 2, val);
}