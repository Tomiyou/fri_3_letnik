#ifndef GUI_H
#define GUI_H

#include <gtkmm-3.0/gtkmm.h>
#include "emulator.hpp"

class EmulatorWindow : public Gtk::Window {
public:
  EmulatorWindow(Emulator *emulator);
  virtual ~EmulatorWindow();

protected:
  Emulator *emulator;
  bool stopped;

  // Execute
  bool executeInstruction();

  // Signal handlers:
  void on_button_start();
  void on_button_execute();
  void on_button_stop();

  // Memory view
  void drawMemoryView();

  // Child widgets:
  Gtk::Box m_Box_All;
  Gtk::Box m_Box_CPU;
  Gtk::Box m_Box_Memory;
  
  Gtk::Button m_Button_Start;
  Gtk::Button m_Button_Execute;
  Gtk::Button m_Button_Stop;

  Gtk::Entry m_Entry_RegisterA;
  Gtk::Entry m_Entry_RegisterX;
  Gtk::Entry m_Entry_RegisterL;
  Gtk::Entry m_Entry_RegisterS;
  Gtk::Entry m_Entry_RegisterT;
  Gtk::Entry m_Entry_RegisterB;
  Gtk::Entry m_Entry_RegisterPC;

  Gtk::TextView m_TextView_Memory;
};

#endif