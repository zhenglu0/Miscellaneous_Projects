#include <stdio.h>
#include <stdlib.h>
#include <setjmp.h>

int main(int argc, char *argv[]) 
{
	int i, restored = 0;
	jmp_buf saved;
	for (i = 0; i < 10; i++) {
		printf("Value of i is now %d\n", i);
		if (i == 5) {
			printf("OK, saving state...\n");
			if (setjmp(saved) == 0) {
				printf("Saved CPU state and breaking from loop.\n");
				break; 
			} else {
				printf("Restored CPU state, continuing where we saved.\n");
				restored = 1;
			}
		}
	}
	if (!restored) {
		printf("Reset the jmp_buf value for saved.\n");
		longjmp(saved, 1);	
	}
	return 0;
}