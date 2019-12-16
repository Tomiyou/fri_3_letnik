cmake_minimum_required(VERSION 3.13)

project(sic_emulator)

find_package(PkgConfig)

pkg_check_modules(GTKMM gtkmm-3.0)

add_subdirectory(src)
