/*
  Other.cpp
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/

#include "Other.h"

point copyboard(int src[3][3], int dest[3][3])
{
	point zero;
	//copy array src to dest
    for (int i = 0; i < 9; i++) {
        dest[i/3][i%3] = src[i/3][i%3];
		if (src[i/3][i%3] == 0) {
			zero.x = i%3;
			zero.y = i/3;
		}
	}
	// return the blank piece
	return zero;
}

//switch operation
void exchange(int &n1, int &n2)
{
	int tmp = n1;
	n1 = n2;
	n2 = tmp;
}

//return the opsosite direction to 
//each possible direction
direction inverse(direction dir)
{
	switch(dir) {
		case UP: 
			return DOWN;
		case DOWN: 
			return UP;
		case LEFT: 
			return RIGHT;
		case RIGHT: 
			return LEFT;
		default: 
			return CENTER;
	}
}

//return the opsosite direction to 
//each possible direction
direction increment(direction dir)
{
	switch(dir) 
	{
		case UP:	
			return RIGHT; 
		case RIGHT:	
			return DOWN; 
		case DOWN:	
			return LEFT; 
		case LEFT:	
			return CENTER; 
		default: 
			return CENTER;
	}
}