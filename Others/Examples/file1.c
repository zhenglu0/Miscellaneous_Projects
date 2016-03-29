#include "file3.h"  /* Declaration made available here */

/* Variable defined here */
int global_variable = 100;    /* Definition checked against declaration */

void increment(void) 
{ 
	global_variable += 10; 
}
