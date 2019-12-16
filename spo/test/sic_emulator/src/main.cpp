#include "gui.hpp"
#include "emulator.hpp"

int main(int argc, char *argv[]) {
  auto app = Gtk::Application::create(argc, argv, "si.fri.th6727.emulator");
  Emulator emulator;

  EmulatorWindow window(&emulator);

  Gtk::Application::create();

  return app->run(window);
}
