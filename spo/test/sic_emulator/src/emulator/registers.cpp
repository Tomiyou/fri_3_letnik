#include "emulator.hpp"
#include "helpers.hpp"
#include <sstream>

WORD Emulator::getReg(RegIndex regno) {
  switch (regno) {
  case RegIndex::A:
    return A;
  case RegIndex::X:
    return X;
  case RegIndex::L:
    return L;
  case RegIndex::B:
    return B;
  case RegIndex::S:
    return S;
  case RegIndex::T:
    return T;
  case RegIndex::F:
    return F;
  case RegIndex::PC:
    return PC;
  default:
    throw "getReg() wrong register number";
  };
}

void Emulator::setReg(RegIndex regno, WORD val) {
  switch (regno) {
  case RegIndex::A:
    A = val;
    break;
  case RegIndex::X:
    X = val;
    break;
  case RegIndex::L:
    L = val;
    break;
  case RegIndex::B:
    B = val;
    break;
  case RegIndex::S:
    S = val;
    break;
  case RegIndex::T:
    T = val;
    break;
  case RegIndex::F:
    F = val;
    break;
  case RegIndex::PC:
    PC = val;
    break;
  default:
    throw "setReg() wrong register number";
  };
}

void Emulator::compare(WORD a, WORD b) {
  if (a < b)
    CC = -1;
  else if (a > b)
    CC = 1;
  else
    CC = 0;
}

std::string Emulator::printRegisterA() {
  std::stringstream stream;
  stream << "A: " << toHex(A, 6);
  return stream.str();
};

std::string Emulator::printRegisterX() {
  std::stringstream stream;
  stream << "X: " << toHex(X, 6);
  return stream.str();
};

std::string Emulator::printRegisterL() {
  std::stringstream stream;
  stream << "L: " << toHex(L, 6);
  return stream.str();
};

std::string Emulator::printRegisterB() {
  std::stringstream stream;
  stream << "B: " << toHex(B, 6);
  return stream.str();
};

std::string Emulator::printRegisterS() {
  std::stringstream stream;
  stream << "S: " << toHex(S, 6);
  return stream.str();
};

std::string Emulator::printRegisterT() {
  std::stringstream stream;
  stream << "T: " << toHex(T, 6);
  return stream.str();
};

std::string Emulator::printRegisterF() {
  std::stringstream stream;
  stream << "F: " << toHex(F, 6);
  return stream.str();
};

std::string Emulator::printRegisterPC() {
  std::stringstream stream;
  stream << "PC: " << toHex(PC, 6);
  return stream.str();
};

std::string Emulator::printRegisterSW() {
  std::stringstream stream;
  stream << "SW: " << toHex(SW, 6);
  return stream.str();
};

