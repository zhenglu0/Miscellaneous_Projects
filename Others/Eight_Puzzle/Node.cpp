/*
  Node.cpp
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/

#include "Node.h"
#include <cmath>
#include <iostream>
#include <string>
#include <unordered_set>

using namespace std;

// constructor for initial state node
node::node(int brd[3][3], int goal_state[])
{
	g = 0;	
	//copy brd into this node's board
	blank = copyboard(brd, board);
	//copy goals
	for (int i = 0; i < 9; ++i)
		goal[i] = goal_state[i];	
	//get manhatan distance
	h = manhatan();	
	//setup linked list
	history[0] = CENTER;
	history[1] = CENTER;
	nextNode = NULL;

}

// constructor for non-initial states
node::node(node *parent, direction dir)
{
	int i;
	point lastblank;
	g = parent->g + 1;
	//make a copY of the parent's board into this node's board
	blank = copyboard(parent->board, board);

	cout << "blank before move " << blank.y <<","
		 << blank.x << endl;

	//move positions according to direction dir
	lastblank = blank;
	switch(dir) {
		case UP:	
			blank.y--;  
			break; 
		case RIGHT:	
			blank.x++;  
			break;
		case DOWN:	
			blank.y++;  
			break;
		case LEFT:	
			blank.x--;  
			break;
		default:
			break;
	}
	exchange(board[lastblank.y][lastblank.x], 
		     board[blank.y][blank.x]);
	
	cout << "blank after move " << blank.y <<","
		 << blank.x << endl;

	//copy goals
	for (int i = 0; i < 9; ++i)
		goal[i] = parent->goal[i];
	//get manhatan distance
	cout << "Node after direction " << dir; 
	h = manhatan();
	//setup linked list
	for (i=1; parent->history[i] != CENTER; i++)
		history[i] = parent->history[i];
	// record history
	history[i] = dir;
	history[i+1] = CENTER;
	nextNode = NULL;
}

void node::insert(node *next)
{
	nextNode = next;
}

node *node::getNext()
{
	return nextNode;
}

int node::f()
{
	return g + h;
}

bool node::canmove(direction dir)
{
	switch(dir) {
		case UP:	
			if (blank.y > 0) 
				return true;
			else 
				return false; 
		case RIGHT:	
			if (blank.x < 2) 
				return true; 
			else 
				return false;
		case DOWN:	
			if (blank.y < 2) 
				return true;
			else 
				return false; 
		case LEFT:	
			if (blank.x > 0) 
				return true;
			else 
				return false; 
		default:
				return false;
	}
	return false;
}

int node::manhatan()
{
	int x, y, pos, result = 0;
	//the goal is defined by putting the position (1 trhough 9) 
	//of the square at the array item with index equal to the 
	//face number of the square.
	//int ending[9] = {4,0,1,2,5,8,7,6,3}; 
	for (int i = 0; i < 9; i++) {
		y = i/3;
		x = i%3;
		pos = board[y][x];

		// print board status
		if (i%3 == 0)
			cout << endl;
		cout << pos << " ";

		result += abs(y - goal[pos]/(int)3);  //add y distance
		result += abs(x - goal[pos]%(int)3);  //add x distance
	}
	cout << endl;
	return result;
}

int node::level()
{
	return g;
}

direction node::getlastdir()
{
	return history[g];
}

void node::printhistory()
{
	int i = 1;
	//Print solution using the history
	while (history[i] != CENTER) {
		switch(history[i]) {
			case UP:	
				cout << "up, "; 
				break;
			case DOWN:	
				cout << "down, "; 
				break;
			case LEFT:	
				cout << "left, "; 
				break;
			case RIGHT:	
				cout << "right, "; 
				break;
			default:
				break;
		}
		i++;
	}
	cout << endl;
}

string node::position()
{
	int x, y, pos;
	string str, result = "";
	for (int i = 0; i < 9; i++) {
		y = i/3;
		x = i%3;
		pos = board[y][x];
		str = to_string(pos);
		result += str;
	}
	return result;
}