/* strtoul example */
#include <stdio.h>
#include <stdlib.h>

int main ()
{
  char szInput [256];
  unsigned long ul;
  printf ("Enter an unsigned number: ");
  fgets (szInput,256,stdin);
  ul = strtoul (szInput,NULL,0);
  printf ("Value entered: %lu. Its double: %lu\n",ul,ul*2);
  return 0;
}
