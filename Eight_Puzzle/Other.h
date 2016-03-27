/*
  Other.h
  Modified by 罗铮 on 03/27/14.
  http://www.Planet-Source-Code.com/vb/scripts/
  ShowCode.asp?txtCodeId=8643&lngWId=3
*/

#ifndef OTHER_H
#define OTHER_H

#define MAX_HISTORY 2020			

// movement direction in the board
enum direction{UP, RIGHT, DOWN, LEFT, CENTER};

// describes a point in the board
struct point{
	int x;
	int y;
};

void exchange(int &n1, int &n2);	//switch two items of a matrix
point copyboard(int src[3][3], 
				int dest[3][3]);	//copy a matrix representing 
									//a board and return the zero position
									//or return the blank position 
direction inverse(direction dir);	//get an inverse direction
direction increment(direction dir);	//get next direction clockwisely

#endif