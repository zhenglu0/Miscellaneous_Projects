// inline_functions_inline.c
#include <stdio.h>

char toupper_function( char a ) {
   return ((a >= 'a' && a <= 'z') ? a-('a'-'A') : a );
}

int main() {
   printf("Enter a character: ");
   char ch = toupper_function( getc(stdin) );
   printf( "%c", ch );
   return 0;
}
