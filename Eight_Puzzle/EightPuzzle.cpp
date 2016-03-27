/*
  EightPuzzle.cpp
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/
 
#include "Node.h"
#include "Queue.h"
#include <iostream>

using namespace std;

int main()
{
	// This test case will case bug, becuase pos value
	// will be out of bound
	
	int initstate[3][3] =  { {1, 5, 2},
							 {4, 3, 6},
							 {0, 7, 8} };
	
	/*					 
	int initstate[3][3] =  { {1, 5, 2},
							 {4, 0, 3},
							 {7, 8, 6} };
	*/		 

	int endstate[3][3] = { {1, 2, 3},
						   {4, 5, 6},
						   {7, 8, 0} };

	//initialize the puzzle
	expsortqueue puzzle(initstate, endstate);
	
	node *best = puzzle.pop();
	int h = best->manhatan();

	while (h != 0 ){
		cout << "best->h = " << h << endl;
		puzzle.expand(best);
		best = puzzle.pop();
		h = best->manhatan();
		if (puzzle.isemptyqueue())
			break;
	}

	if (h == 0) {
		cout << "Solution found!" << endl;
		cout << "The sequence to get to the goal is: ";
		best->printhistory();
	} else
		cout << "Problem has no solution, search tree exausted. "
			    "Expanded " << best->level() << " levels" << endl;

	cout << puzzle.nodesexpanded << " nodes expanded" << endl;
	
	delete best;
}
