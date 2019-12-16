#include "emulator.hpp"
#include <bitset>
#include <sstream>

bool Emulator::execSICF3F4(BYTE opcode, BYTE bitsNI, WORD displacement) {
  if (opcode == 60 && bitsNI == 3 && displacement == 12285) {
    // check for: halt J halt
    halt = true;

    std::cout << "HALT detected\n";
  }
  
  // parse all bits from the 2 byte displacement
  bool bitX = (displacement >> 15) & 1;
  BYTE bitsBP = (displacement >> 13) & 3;
  bool bitE = (displacement >> 12) & 1;

  // remove upper bits of displacement
  if (bitsNI == 0) {
    displacement &= 32767; // SIC, keep 15 low bits
  } else {
    std::cout << "F3/F4: " << std::bitset<32>(displacement) << "__"
              << std::bitset<32>(displacement & 4095) << "\n";
    displacement &= 4095; // F3/F4, keep 12 low bits
  }

  // check if operation is of F4, fetch additional byte
  if (bitE) {
    displacement = (displacement << 16) | fetch();
  }

  // calculate UN address
  WORD operandAddress;
  if (bitsBP == 0 || bitsNI == 0) {
    // Direct
    operandAddress = displacement;
  } else if (bitsBP == 1) {
    // PC-relative
    operandAddress =
        displacement > 2047 ? PC - (4096 - displacement) : PC + displacement;

    std::cout << "PC-relative: " << std::bitset<32>(displacement) << "__"
              << std::bitset<32>(operandAddress) << "\n";
  } else if (bitsBP == 2) {
    // Base-relative
    operandAddress = B + displacement;
  } else {
    return false;
  }

  // check if index addressing is enabled
  if (bitX) {
    operandAddress += X;
  }

  WORD operand;
  if (bitsNI == 3 || bitsNI == 0) {
    // enostavno
    operand = getWord(operandAddress);
  } else if (bitsNI == 1) {
    // takojsnje
    operand = operandAddress;
  } else {
    // posredno
    operandAddress = getWord(operandAddress);
    operand = getWord(operandAddress);
  }

  std::cout << std::bitset<8>(opcode) << " | " << std::bitset<32>(displacement)
            << " | " << std::bitset<32>(operandAddress) << " | "
            << std::bitset<32>(operand) << " | "
            << std::bitset<8>((BYTE)Opcode::LDX) << "\n\n";

  switch (static_cast<Opcode>(opcode)) {
  case Opcode::ADD:
    A += operand;
    break;
  case Opcode::ADDF:
    break;
  case Opcode::AND:
    A = A & operand;
    break;
  case Opcode::COMP:
    compare(A, operand);
    break;
  case Opcode::COMPF:
    break;
  case Opcode::DIV:
    A /= operand;
    break;
  case Opcode::DIVF:
    break;
  case Opcode::J:
    PC = operandAddress;
    break;
  case Opcode::JEQ:
    if (CC == 0)
      PC = operandAddress;
    break;
  case Opcode::JGT:
    if (CC == 1)
      PC = operandAddress;
    break;
  case Opcode::JLT:
    if (CC == -1)
      PC = operandAddress;
    break;
  case Opcode::JSUB:
    L = PC;
    PC = operandAddress;
    break;
  case Opcode::LDA:
    A = operand;
    break;
  case Opcode::LDB:
    B = operand;
    break;
  case Opcode::LDCH:
    A = operand >> 16;
    break;
  case Opcode::LDF:
    break;
  case Opcode::LDL:
    L = operand;
    break;
  case Opcode::LDS:
    S = operand;
    break;
  case Opcode::LDT:
    T = operand;
    break;
  case Opcode::LDX:
    X = operand;
    break;
  case Opcode::LPS:
    break;
  case Opcode::MUL:
    A *= operand;
    break;
  case Opcode::MULF:
    break;
  case Opcode::OR:
    A |= operand;
    break;
  case Opcode::RD:
    break;
  case Opcode::RSUB:
    PC = L;
    break;
  case Opcode::SSK:
    break;
  case Opcode::STA:
    setWord(operandAddress, A);
    break;
  case Opcode::STB:
    setWord(operandAddress, B);
    break;
  case Opcode::STCH:
    setByte(operand, A);
    break;
  case Opcode::STF:
    break;
  case Opcode::STI:
    break;
  case Opcode::STL:
    setWord(operandAddress, L);
    break;
  case Opcode::STS:
    setWord(operandAddress, S);
    break;
  case Opcode::STSW:
    setWord(operandAddress, SW);
    break;
  case Opcode::STT:
    setWord(operandAddress, T);
    break;
  case Opcode::STX:
    setWord(operandAddress, X);
    break;
  case Opcode::SUB:
    A -= operand;
    break;
  case Opcode::SUBF:
    break;
  case Opcode::TD:
    break;
  case Opcode::TIX:
    X += 1;
    compare(X, operand);
    break;
  case Opcode::WD:
    break;

  default:
    return false;
  }

  return true;
};
