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
TARGET =Array \
		broken \
		call_back1 \
		call_back2 \
		card \
		CharArray \
		Closest_pair \
		CompareToAll \
		CompareToMax \
		extern \
		Fibonaci \
		file2 \
		fork \
		getErrorString \
		getopt \
		HeapSort \
		hexdump \
		Ignore \
		isspace \
		jump \
		log2_function \
		new_delete \
		Numeric_Limits \
		PermuteStringSTL \
		Prefix_Postfix2 \
		Prefix_Postfix \
		PrefixSum \
		Program \
		pthread \
		Queue \
		random \
		ReadCommandLine \
		riemannIntegral \
		RightShift \
		SetPrecision \
		signal \
		signal_cpp \
		SizeofArray \
		square_root \
		STL_vector_int \
		strtoul \
		strtoull \
		toupper \
		toupper_function \
		toupper_macro \
		TwoDArray \
		Phone \
		Horspool


all: $(TARGET)

square_root: square_root.c
	$(CC) $(CFLAGS) $< -o $@ -lm

Closest_pair: Closest_pair.c 
	$(CC) $(CFLAGS) $< -o $@ -lm

riemannIntegral:riemannIntegral.c
	$(CC) $(CFLAGS) $< -o $@ -lm

pthread: pthread.c
	$(CC) $(CFLAGS) $< -o $@ -lm -pthread
	
file2: file1.o

Phone: Phone.java
	javac Phone.java

Horspool: Horspool.java
	javac Horspool.java

.c.o:
	$(CC) $(CFLAGS) -c $< -o $@ 

.cpp.o:
	$(CXX) $(CXXFLAGS) -c $< -o $@ 

clean:
	$(RM) $(TARGET) *.o *.class
