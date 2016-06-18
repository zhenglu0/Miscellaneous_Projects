/*
  Queue.h
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/

#ifndef QUEUE_H
#define QUEUE_H

#include <string>
#include <unordered_set>

using namespace std;

class expsortqueue {
private: 
	node *first; 			//pointer to first node in queue (biggest f(n))
	node *last;				//pointer to last node in queue (smallest f(n))
	int initial[3][3];		//initial state
	int goal[9]; 			//goal state
	unordered_set<string>  visits; //hashset to store states already visited 
	
public:
	int queuesize; 				//just for testing
	int nodesexpanded; 			//just for testing
	expsortqueue(int init[3][3], 
				 int ending[3][3]);
	~expsortqueue();			//destructor
	node *pop();				//get node on top of the queue
	void pushnsort(node *newnode);//put a new node in the queue 
	void expand(node *parent);	//expands a node 
	bool isemptyqueue();		//determines if there are more states left
	bool wasvisited(node *square);
	void dovisit(node *square);
};

#endif