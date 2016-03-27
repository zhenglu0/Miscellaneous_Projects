#
# Makefile for lab8
#

##############################################################
# Compiler and flags to use
##############################################################

CXX       = g++
CXXFLAGS  = -g -Wall -Werror -std=c++0x

##############################################################
# You shouldn't have to change anything below this line.
##############################################################


LAB8_EXE  = EightPuzzle
LAB8_OBJS = EightPuzzle.o Node.o Queue.o Other.o

###

$(LAB8_EXE): $(LAB8_OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $(LAB8_OBJS)

clean:
	rm -f  $(LAB8_EXE) $(LAB8_OBJS)

###

.cpp.o:
	$(CXX) $(CXXFLAGS) -c $<

###

EightPuzzle.o: EightPuzzle.cpp Node.h Node.cpp \
	Queue.h Queue.cpp Other.h Other.cpp

Other.o: Other.h Other.cpp

Node.o: Node.h Node.cpp

Queue.o: Node.h Node.cpp Queue.h Queue.cpp