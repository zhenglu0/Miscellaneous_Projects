#ifndef _H_BOGGLE
#define _H_BOGGLE

#include <vector>
#include <set>
#include <string>

#include "trie.h"

class Boggle {
public:
  typedef std::vector<char> row;
  typedef std::vector<row> board;
  
  void read_dictionary(const char *filename);
  void read_board(const char *filename);
  void show_board();
  void solve_board();
  void print_result();

private:
  void do_solve_board(int x, int y);
  void set_visited_board();
  void show_visited_board();
  void print_board(const board &b);
  Trie dict;
  board boggle_board;
  board visited_board;
  std::string word;
  unsigned int n;
  std::set<std::string> result;
};

#endif /* _H_BOGGLE */
