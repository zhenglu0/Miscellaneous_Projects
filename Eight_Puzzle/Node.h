/*
  Node.h
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/

#ifndef NODE_H
#define NODE_H

#include "Other.h"
#include <string>

using namespace std;

// Node representing a state of the 8-puzzle
class node {
private:
	node *nextNode;				    //next node in the queue
	int h, g; 						//heuristics and path cost
	int goal[9]; 					//goal state
	direction history[MAX_HISTORY]; //keeps track of the movement 
									//of the square 0
public:
	point blank;						//position of blank space (0)
	node(node *parent, direction dir);	//constructor
	node(int brd[3][3], int goal[]);	//constructor
	int board[3][3];					//8-puzzle board
	void insert(node *next);			//insert node (update nextNode)
	node *getNext();					//get the address of next node
	int f();							//return f(n)
	int manhatan();						//return sum of manhatan distance
										// of all square		
	bool canmove(direction dir);		//can the square be moved to 
										// direction dir?
	int level();						//returns level based on g(n)
	direction getlastdir();				//returns the direction taken 
										//to arrive to the current position
	void printhistory();				//prints out the solution 
	string position();					//hash value of the position
};

#endif