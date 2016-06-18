#include <stdio.h>

char *GetErrorString( int x )
{
  char* errorString = NULL;

  switch ( x ) {
    case 0:
      errorString = "Success -- No error.";
      break;
    case 2:
      errorString = "Overflow!";
      break;
    default:
      break;
  }

  return errorString;
}

int main(void)
{
  int err = 1;
  if ( err ) {
      printf( "%s\n", GetErrorString( err ) );
  }
  return 0;
}
