// inline_functions_macro.c
#include <stdio.h>

#define toupper_macro(a) ((a) >= 'a' && ((a) <= 'z') ? ((a)-('a'-'A')):(a))

int main() {
   char ch;
   printf("Enter a character: ");
   ch = toupper_macro( getc(stdin) );
   printf( "%c", ch );
   return 0;
}
