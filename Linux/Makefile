# the compiler: g++ for C++ program, define as g++ for C++
CXX = g++
CC = gcc
RM = rm -f
CFLAGS = $(CXXFLAGS)
# compiler flags:
#  -g    adds debugging information to the executable file
#  -Wall turns on most, but not all, compiler warnings
CXXFLAGS  = -Wall -Werror
#LDFLAGS = -lm
SPECIAL_FLAGS = -std=c++0x -D_GNU_SOURCE

# the build target executable:
TARGET = cpu_mask \
		 thread_migration \



all: $(TARGET)

cpu_mask: cpu_mask.c
	$(CC) $(CFLAGS) -D_GNU_SOURCE $< -o $@ -lm -pthread

thread_migration: thread_migration.c
	$(CC) $(CFLAGS) -D_GNU_SOURCE $< -o $@ -lm -pthread
	
.c.o:
	$(CC) $(CFLAGS) -c $< -o $@ 

.cpp.o:
	$(CXX) $(CXXFLAGS) -c $< -o $@ 

clean:
	$(RM) $(TARGET) *.o *.class
