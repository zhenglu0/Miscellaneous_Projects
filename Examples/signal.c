#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void signalHandler( int signum )
{
    printf("Interrupt signal (%d) received.\n", signum);

    // cleanup and close up stuff here  
    // terminate program  
    // exit(signum);  

}

int main ()
{
    // register signal SIGINT and signal handler  
    signal(SIGINT, signalHandler);  
	//signal(SIGKILL, signalHandler);
	signal(SIGTERM, signalHandler);
    
	while(1){
       printf ("Going to sleep....\n");
       sleep(1);
    }

    return 0;
}
