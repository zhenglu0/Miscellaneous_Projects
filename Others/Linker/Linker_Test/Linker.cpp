
//#include "Linker.h"

/* Initialized global variable */
int z_global = 11;
/* Second global named y_global_init, but they are both static */
static int y_global_init = 2;
/* Declaration of another global variable */
extern int x_global_init;

int fn_a(int x, int y)
{
    return(x+y);
}

int main(int argc, char *argv)
{
    const char *message = "Hello, world";
    
    return fn_a(11,12);
}