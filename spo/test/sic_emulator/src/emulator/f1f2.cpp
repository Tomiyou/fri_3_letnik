#include "emulator.hpp"
#include <sstream>

bool Emulator::execF1(BYTE opcode) {
  switch (static_cast<Opcode>(opcode)) {
  case Opcode::FIX:
    break;
  case Opcode::FLOAT:
    break;
  case Opcode::HIO:
    break;
  case Opcode::NORM:
    break;
  case Opcode::SIO:
    break;
  case Opcode::TIO:
    break;

  default:
    return false;
  }

  return true;
};

bool Emulator::execF2(BYTE opcode, BYTE operand) {
  RegIndex r1 = static_cast<RegIndex>(operand >> 4);
  RegIndex r2 = static_cast<RegIndex>(operand & 15);

  switch (static_cast<Opcode>(opcode)) {
  case Opcode::ADDR:
    setReg(r2, getReg(r2) + getReg(r1));
    return true;
  case Opcode::CLEAR:
    setReg(r2, 0);
    return true;
  case Opcode::COMPR:
    compare(getReg(r1), getReg(r2));
    return true;
  case Opcode::DIVR:
    setReg(r2, getReg(r2) / getReg(r1));
    return true;
  case Opcode::MULR:
    setReg(r2, getReg(r2) * getReg(r1));
    return true;
  case Opcode::RMO:
    setReg(r2, getReg(r1));
    return true;
  case Opcode::SHIFTL:
    return true;
  case Opcode::SHIFTR:
    return true;
  case Opcode::SUBR:
    setReg(r2, getReg(r2) - getReg(r1));
    return true;
  case Opcode::SVC:
    return true;
  case Opcode::TIXR:
    setReg(RegIndex::X, getReg(RegIndex::X) + 1);
    compare(X, operand);
    return true;

  default:
    return false;
  }
};