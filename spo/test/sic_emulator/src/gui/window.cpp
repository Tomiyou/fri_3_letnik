#include "gui.hpp"
#include "helpers.hpp"

EmulatorWindow::EmulatorWindow(Emulator *emulator)
    : emulator(emulator),
      m_Box_All(Gtk::ORIENTATION_HORIZONTAL),
      m_Box_CPU(Gtk::ORIENTATION_VERTICAL),
      m_Box_Memory(Gtk::ORIENTATION_VERTICAL), m_Button_Start("Start"),
      m_Button_Execute("Execute"), m_Button_Stop("Stop"), stopped(true) {
  set_size_request(800, 300);
  set_resizable(false);
  set_title("SIC emulator");

  // set box sizes
  m_Box_CPU.set_size_request(60);
  m_Box_Memory.set_size_request(440);

  m_TextView_Memory.set_editable(false);
  m_TextView_Memory.set_monospace(true);

  // initialize entries
  m_Entry_RegisterA.set_max_length(9);
  m_Entry_RegisterB.set_max_length(9);
  m_Entry_RegisterL.set_max_length(9);
  m_Entry_RegisterS.set_max_length(9);
  m_Entry_RegisterT.set_max_length(9);
  m_Entry_RegisterX.set_max_length(9);
  m_Entry_RegisterPC.set_max_length(10);

  m_Entry_RegisterA.set_editable(false);
  m_Entry_RegisterB.set_editable(false);
  m_Entry_RegisterL.set_editable(false);
  m_Entry_RegisterS.set_editable(false);
  m_Entry_RegisterT.set_editable(false);
  m_Entry_RegisterX.set_editable(false);
  m_Entry_RegisterPC.set_editable(false);

  m_Entry_RegisterA.set_text("A: 000000");
  m_Entry_RegisterB.set_text("B: 000000");
  m_Entry_RegisterL.set_text("L: 000000");
  m_Entry_RegisterS.set_text("S: 000000");
  m_Entry_RegisterT.set_text("T: 000000");
  m_Entry_RegisterX.set_text("X: 000000");
  m_Entry_RegisterPC.set_text("PC: 000000");

  // connect buttons
  m_Button_Stop.signal_clicked().connect(
      sigc::mem_fun(*this, &EmulatorWindow::on_button_stop));
  m_Button_Start.signal_clicked().connect(
      sigc::mem_fun(*this, &EmulatorWindow::on_button_start));
  m_Button_Execute.signal_clicked().connect(
      sigc::mem_fun(*this, &EmulatorWindow::on_button_execute));

  // Put widgets on window
  m_Box_CPU.pack_start(m_Button_Start);
  m_Box_CPU.pack_start(m_Button_Stop);
  m_Box_CPU.pack_start(m_Button_Execute);

  m_Box_CPU.pack_start(m_Entry_RegisterA);
  m_Box_CPU.pack_start(m_Entry_RegisterB);
  m_Box_CPU.pack_start(m_Entry_RegisterL);
  m_Box_CPU.pack_start(m_Entry_RegisterS);
  m_Box_CPU.pack_start(m_Entry_RegisterT);
  m_Box_CPU.pack_start(m_Entry_RegisterX);
  m_Box_CPU.pack_start(m_Entry_RegisterPC);

  m_Box_Memory.pack_start(m_TextView_Memory);

  m_Box_All.pack_start(m_Box_CPU);
  m_Box_All.pack_start(m_Box_Memory);

  add(m_Box_All);
  show_all_children();

  // load object file to memory
  emulator->load("tretji_del.obj");

  drawMemoryView();
}

EmulatorWindow::~EmulatorWindow() {}

void EmulatorWindow::on_button_start() {
  stopped = false;
  
  while (!stopped && !executeInstruction()) {}

  stopped = true;
}

void EmulatorWindow::on_button_stop() {
  stopped = true;
}

void EmulatorWindow::on_button_execute() {
  executeInstruction();
}

void EmulatorWindow::drawMemoryView() {
  WORD address = emulator->getMemoryViewAddress();

  if ((int)address < 0) return;

  std::string str;
  str.reserve(2000);
  std::stringstream sstr(str);
  
  for (int i = 0; i < 16; i++) {
    sstr << toHex(address, 5);
    for (int j = 0; j < 16; j++) {
      sstr << " " << toHex(emulator->getByte(address + j), 2);
    }
    sstr << "\n";

    address += 16;
  }

  auto textBuffer = m_TextView_Memory.get_buffer();
  textBuffer->set_text(sstr.str());
}

bool EmulatorWindow::executeInstruction() {
  bool halt = emulator->execute();
  
  m_Entry_RegisterA.set_text(emulator->printRegisterA());
  m_Entry_RegisterB.set_text(emulator->printRegisterB());
  m_Entry_RegisterL.set_text(emulator->printRegisterL());
  m_Entry_RegisterS.set_text(emulator->printRegisterS());
  m_Entry_RegisterT.set_text(emulator->printRegisterT());
  m_Entry_RegisterX.set_text(emulator->printRegisterX());
  m_Entry_RegisterPC.set_text(emulator->printRegisterPC());

  drawMemoryView();

  return halt;
}
