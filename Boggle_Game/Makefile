#
# Makefile for lab8
#

##############################################################
# Compiler and flags to use
##############################################################

CXX       = g++
CXXFLAGS  = -g -Wall -Werror -O3

##############################################################
# You shouldn't have to change anything below this line.
##############################################################


LAB8_EXE  = boggle
LAB8_OBJS = boggle_main.o boggle.o trie.o 

###

$(LAB8_EXE): $(LAB8_OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $(LAB8_OBJS)

clean:
	rm -f  $(LAB8_EXE) $(LAB8_OBJS)

###

.cpp.o:
	$(CXX) $(CXXFLAGS) -c $<

###

boggle_main.o: boggle_main.cpp boggle.h boggle.cpp trie.h trie.cpp

boggle.o: boggle.h boggle.cpp

trie.o: trie.h trie.cpp
