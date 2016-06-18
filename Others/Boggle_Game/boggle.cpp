#include "boggle.h"
#include <iostream>
#include <stdexcept>
#include <fstream>
#include <cmath>

using std::string;
using std::ifstream;

template <typename T>
inline T square(T x) {
  return x*x;
}

void Boggle::read_dictionary(const char *filename) {
  ifstream f(filename);
  if (!f.is_open())
    throw std::runtime_error("failed to open dictionary file");

  while(f.good()) {
    string s;
    getline(f,s);
    if (s.size()>0)
      dict.addWord(s);
  }
}

void Boggle::read_board(const char *filename) {
  ifstream f(filename);
  if (!f.is_open())
    throw std::runtime_error("Failed to open board file");

  std::vector<char> letters;

  char c;
  while (f >> c) {
    letters.push_back(c);
  }

  n = std::sqrt(letters.size());

  if (square(n) != letters.size())
    throw std::runtime_error("board file is not NxN");

  board result;
  for (unsigned int i=0;i<n;++i) {
    row r;
    for (unsigned int j=0;j<n;++j) {
      r.push_back(letters[(i*n)+j]);
    }
    result.push_back(r);
  }

  boggle_board=result;
}

void Boggle::show_board()
{
  print_board(boggle_board);
}

void Boggle::solve_board() 
{
  set_visited_board();

  for (unsigned int i = 0; i < n; ++i)
    for (unsigned int j = 0; j < n; ++j)
      do_solve_board(i, j);
}

void Boggle::print_result()
{
  for (std::set<std::string>::iterator it=result.begin(); it!=result.end(); ++it)
    std::cout << *it << std::endl;
  std::cout << std::endl;
}

void Boggle::do_solve_board(int x, int y)
{
  word.push_back(boggle_board[x][y]);
  if (!dict.searchPrefix(word)) {
    word.erase(word.size()-1, 1);
    return;
  }

  visited_board[x][y] = 'T'; 

  if (word.length()>= 3 && dict.searchWord(word))
    result.insert(word);  
  
  int d = static_cast<int>(n);
 
  if (x-1>=0 && x-1<d && y-1>=0 && y-1<d && 
      visited_board[x-1][y-1] == 'F')
    do_solve_board(x-1, y-1);

  if (x-1>=0 && x-1<d && y>=0 && y<d && 
      visited_board[x-1][y] == 'F')
    do_solve_board(x-1, y);

  if (x-1>=0 && x-1<d && y+1>=0 && y+1<d && 
      visited_board[x-1][y+1] == 'F')
    do_solve_board(x-1, y+1);

  if (x>=0 && x<d && y-1>=0 && y-1<d && 
      visited_board[x][y-1] == 'F')
    do_solve_board(x, y-1);

  if (x>=0 && x<d && y+1>=0 && y+1<d && 
      visited_board[x][y+1] == 'F')
    do_solve_board(x, y+1);

  if (x+1>=0 && x+1<d && y-1>=0 && y-1<d && 
      visited_board[x+1][y-1] == 'F')
    do_solve_board(x+1, y-1);

  if (x+1>=0 && x+1<d && y>=0 && y<d && 
      visited_board[x+1][y] == 'F')
    do_solve_board(x+1, y);

  if (x+1>=0 && x+1<d && y+1>=0 && y+1<d && 
      visited_board[x+1][y+1] == 'F')
    do_solve_board(x+1, y+1);

  word.erase(word.size()-1, 1);
  visited_board[x][y] = 'F';
}

void Boggle::print_board(const board &b)
{
  board::const_iterator i = b.begin();
  board::const_iterator e = b.end();

  while (i != e) {
    row::const_iterator j = i->begin();

    while (j != i->end())
        std::cout<<*j++<<" ";

    ++i;
    std::cout<<std::endl;
  }
}

// 'T' means the position is visited
// 'F' means the position is not visited
void Boggle::set_visited_board()
{
  for (unsigned int i=0;i<n;++i) {
    row r;
    for (unsigned int j=0;j<n;++j) {
      r.push_back('F');
    }
    visited_board.push_back(r);
  }
}

void Boggle::show_visited_board()
{
  print_board(visited_board);
}

