#include "emulator.hpp"
#include <string>

// read 1 character / half a byte
char readChar(std::ifstream &objectFile) {
  char byte;
  objectFile >> byte;
  return byte;
}

// read 2 characters / 1 bytes
BYTE readByte(std::ifstream &objectFile) {
  char data[2];
  objectFile >> data;
  return std::stoi(data, 0, 16);
}

// read 6 characters / 3 bytes / 1 word
WORD readWord(std::ifstream &objectFile) {
  char data[6];
  objectFile >> data;
  return std::stoi(data, 0, 16);
}

// read 6 characters / 3 bytes / 1 word
WORD readWord(std::ifstream &objectFile, int *counter) {
  char data[6];
  objectFile >> data;

  for (int i = 0; i < 6; i++) {
    if (data[i] == 0) {
      break;
    }

    *counter = (i + 1) / 2;
  }
  return std::stoi(data, 0, 16);
}

// read len characters as string
std::string readString(std::ifstream &objectFile, int len) {
  char data[len];
  objectFile >> data;

  std::string str(data);
  return str;
}

// load .obj file into SIC/XE memory
void Emulator::load(std::string path) {
  // open object file
  std::ifstream objectFile;
  objectFile.open(path);
  if (objectFile.fail()) {
    throw "Error opening object file!";
  }
  
  // currently all three unused
  std::string programName;
  WORD initialAddress = 0;
  int objLength = 0;

  while (!objectFile.eof()) {
    char entryType = readChar(objectFile);

    if (entryType == 'T') {
      WORD entryAddress = readWord(objectFile);
      int entryLength = readByte(objectFile);

      while (entryLength > 0) {
        int counter = 0;
        WORD memoryWord = readWord(objectFile, &counter);
        setWord(entryAddress, memoryWord << ((3 - counter) * 8));
        
        // each word is long 3 bytes
        entryAddress += counter;
        entryLength -= counter;
      }
    } else if (entryType == 'H') {
      programName = readString(objectFile, 6);
      initialAddress = readWord(objectFile);
      objLength = readWord(objectFile);
    } else if (entryType == 'E') {      
      PC = readWord(objectFile);
      return;
    } else {
      throw "Unknown entry when reading OBJ file!";
    }
  }

  throw "Error reading OBJ file!";
}