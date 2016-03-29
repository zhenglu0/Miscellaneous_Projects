#include "file3.h"
#include <stdio.h>

void use_it(void)
{
	printf("Global variable: %d\n", ++global_variable);
}

int main()
{
	increment();
	use_it();
	
	return 0;
}
