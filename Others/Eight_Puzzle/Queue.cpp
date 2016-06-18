/*
  Queue.cpp
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/

#include "Node.h"
#include "Queue.h"
#include <iostream>
#include <unordered_set>

using namespace std;

expsortqueue::expsortqueue(int init[3][3], int ending[3][3])
{
	//copy starting and ending states
	for (int i = 0; i < 9; i++) {
		goal[ending[i/3][i%3]] = i;
		initial[i/3][i%3] = init[i/3][i%3];
	}
	//initialize linked list
	first = new node(initial, goal);
	last = first;
	queuesize = 1;
	nodesexpanded = 0;
}

expsortqueue::~expsortqueue()
{
	node *i = pop();
	while (i != NULL) {
		delete i;
		i = pop();
	}
}

// create the children for parent node
void expsortqueue::expand(node *parent)
{
	node *newnode;
	//loop clockwise
	for (direction i = UP; i != CENTER; i = increment(i)) {
		//make just the necessary objects
		//avoid making imposible states and states 
		//coming from the state before parent
		if (inverse(parent->getlastdir()) != i && 
			parent->canmove(i)) {

			cout << "parent info!!!"; 
			for (int j = 0; j < 9; j++) {
				int y = j/3;
				int x = j%3;
				int pos = parent->board[y][x];

				// print board status
				if (j%3 == 0)
					cout << endl;
				cout << pos << " ";
			}
			cout << endl << "parent printing finished" << endl;
			cout << "parent direction moving " << i << endl;
			cout << "parent blank " << (parent->blank).y << ","
				 << (parent->blank).x << endl;

			newnode = new node(parent, i);
			//don't push new state if it was already used
			if (!wasvisited(newnode)){
				nodesexpanded++;
				pushnsort (newnode);
			} else {
				delete newnode;
			}
		}
	}
	dovisit(parent); //register use of state of parent node
	delete parent;
}

void expsortqueue::dovisit(node *square)
{
	//get position number of the board of state "square"
	string pos = square->position(); 
	//mark position as visited 
	visits.insert(pos);
}

bool expsortqueue::wasvisited(node *square)
{
	//get position number of the board of state "square"
	string pos = square->position(); 
	cout << "pos = " << pos << endl;
	//determine if position has been visited
	unordered_set<string>::const_iterator got = visits.find (pos);
	if ( got == visits.end() )
		return false;
	else
		return true;
}

//return first node in priority queue (delete it from queue)
node* expsortqueue::pop()
{
	node *oldfirst = first;

	if (first != NULL) 
		first = first->getNext();

	queuesize--;
	return oldfirst;
}

//push node to queue
void expsortqueue::pushnsort(node *newNode)
{
	//in case the node is the first one to be pushed
	if ((first == NULL) || 
		(first->f() > newNode->f())){
		newNode->insert(first);
		first = newNode;
	} else {
		//look for ordered place in queue
		//it is assending order by f()
		node *n = first->getNext();
		node *prev = first; 
		while (n != NULL) {
			if (newNode->f() < n->f()) {
				prev->insert(newNode);
				newNode->insert(n);
				break;
			}	
			prev = n;
			n = n->getNext();
		}
	}
	//in case the node is the last one to be pushed
	if (newNode->getNext() == NULL) {
		last->insert(newNode);
		last = newNode;
	}
	queuesize++;
}


bool expsortqueue::isemptyqueue()
{
	return (first == NULL);
}
