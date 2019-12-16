link_directories( ${GTKMM_LIBRARY_DIRS} )
include_directories( ${GTKMM_INCLUDE_DIRS} )
# include_directories( "../include" )
include_directories(${CMAKE_SOURCE_DIR}/include)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

file( GLOB GUI_SOURCES "gui/*.cpp" )
file( GLOB EMULATOR_SOURCES "emulator/*.cpp" )
file( GLOB HELPERS_SOURCES "helpers/*.cpp" )

add_executable( emulator main.cpp ${EMULATOR_SOURCES} ${GUI_SOURCES}  ${HELPERS_SOURCES} )

target_link_libraries( emulator ${GTKMM_LIBRARIES} )
