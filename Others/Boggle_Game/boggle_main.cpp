#include "boggle.h"
#include <iostream>

int main(int argc, char* argv[])
{
  
  if (argc != 3 ) {
    std::cout << "Usage example: ./boggle board1.txt dict.txt" << std::endl;  
    return -1;
  }
  
  Boggle boggle;
  boggle.read_dictionary(argv[2]); 
  boggle.read_board(argv[1]);
  boggle.solve_board();
  boggle.print_result();
  
  return 0;
}
