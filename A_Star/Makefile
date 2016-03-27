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


LAB8_EXE  = Astar
LAB8_OBJS = Astar.o

###

$(LAB8_EXE): $(LAB8_OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $(LAB8_OBJS)

clean:
	rm -f  $(LAB8_EXE) $(LAB8_OBJS)

###

.cpp.o:
	$(CXX) $(CXXFLAGS) -c $<

###

Astar.o: Astar.cpp Astar.h