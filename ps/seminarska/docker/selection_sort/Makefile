CXX = dpcpp
CXXFLAGS = -o
LDFLAGS = -lOpenCL -lsycl
EXE_NAME = selection_sort
SOURCES = selection_sort.cpp

all: main

main:
	$(CXX) $(CXXFLAGS) $(EXE_NAME) $(SOURCES) $(LDFLAGS)

run: 
	./$(EXE_NAME)

clean: 
	rm -rf $(EXE_NAME)