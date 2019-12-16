#ifndef EMULATOR_H
#define EMULATOR_H
#define MAX_ADDRESS 1048575

#include <fstream>
#include <iostream>
#include <string>

typedef unsigned int WORD;
typedef unsigned char BYTE;
typedef WORD Register;

enum class RegIndex : int {
  A = 0,
  X = 1,
  L = 2,
  B = 3,
  S = 4,
  T = 5,
  F = 6,
  PC = 7
};

enum class Opcode : BYTE {
  ADD = 0x18,
  ADDF = 0x58,
  ADDR = 0x90,
  AND = 0x40,
  CLEAR = 0xB4,
  COMP = 0x28,
  COMPF = 0x88,
  COMPR = 0xA0,
  DIV = 0x24,
  DIVF = 0x64,
  DIVR = 0x9C,
  FIX = 0xC4,
  FLOAT = 0xC0,
  HIO = 0xF4,
  J = 0x3C,
  JEQ = 0x30,
  JGT = 0x34,
  JLT = 0x38,
  JSUB = 0x48,
  LDA = 0x00,
  LDB = 0x68,
  LDCH = 0x50,
  LDF = 0x70,
  LDL = 0x08,
  LDS = 0x6C,
  LDT = 0x74,
  LDX = 0x04,
  LPS = 0xD0,
  MUL = 0x20,
  MULF = 0x60,
  MULR = 0x98,
  NORM = 0xC8,
  OR = 0x44,
  RD = 0xD8,
  RMO = 0xAC,
  RSUB = 0x4C,
  SHIFTL = 0xA4,
  SHIFTR = 0xA8,
  SIO = 0xF0,
  SSK = 0xEC,
  STA = 0x0C,
  STB = 0x78,
  STCH = 0x54,
  STF = 0x80,
  STI = 0xD4,
  STL = 0x14,
  STS = 0x7C,
  STSW = 0xE8,
  STT = 0x84,
  STX = 0x10,
  SUB = 0x1C,
  SUBF = 0x5C,
  SUBR = 0x94,
  SVC = 0xB0,
  TD = 0xE0,
  TIO = 0xF8,
  TIX = 0x2C,
  TIXR = 0xB8,
  WD = 0xDC,
};

class Emulator {
private:
  bool halt;

  Register A;
  Register X;
  Register L;
  Register B;
  Register S;
  Register T;
  Register F;
  Register PC;
  Register SW;
  int CC;

  BYTE memory[MAX_ADDRESS + 1];
  bool memoryChanged;

  WORD getReg(RegIndex regno);
  void setReg(RegIndex regno, WORD val);

  void setByte(WORD addr, WORD val);
  void setWord(WORD addr, WORD val);

  BYTE fetch();
  bool execF1(BYTE opcode);
  bool execF2(BYTE opcode, BYTE operand);
  bool execSICF3F4(BYTE opcode, BYTE bitsNI, WORD displacement);

  void compare(WORD a, WORD b);

public:
  Emulator();
  ~Emulator();

  WORD getByte(WORD addr);
  WORD getWord(WORD addr);
  void load(std::string path);
  int getMemoryViewAddress();

  bool execute();

  std::string printRegisterA();
  std::string printRegisterX();
  std::string printRegisterL();
  std::string printRegisterB();
  std::string printRegisterS();
  std::string printRegisterT();
  std::string printRegisterF();
  std::string printRegisterPC();
  std::string printRegisterSW();
};

#endif
