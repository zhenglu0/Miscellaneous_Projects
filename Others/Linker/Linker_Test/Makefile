#
# Makefile Linker
#

##############################################################
# Compiler and flags to use
##############################################################

CXX       = gcc
CXXFLAGS  = -g -Wall

##############################################################
# You shouldn't have to change anything below this line.
##############################################################

LAB5_EXE  = Linker
LAB5_OBJS = Linker.o
###

$(LAB5_EXE): $(LAB5_OBJS)
	$(CXX) -o $(LAB5_EXE) $(LAB5_OBJS)

clean:
	rm -f $(LAB5_EXE) $(LAB5_OBJS)

.PHONY: clean

###

.cc.o:
	$(CXX) $(CXXFLAGS) -c $<

###

Linker.o: Linker.cpp 
