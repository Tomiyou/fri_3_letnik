#include "emulator.hpp"

Emulator::Emulator()
    : A(0), X(0), L(0), B(0), S(0), T(0), F(0), PC(0), SW(0),
      memoryChanged(true), halt(true) {}

Emulator::~Emulator() {}
