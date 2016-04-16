/* Includes */
//#define _GNU_SOURCE
#include <unistd.h>     /* Symbolic Constants */
#include <sys/types.h>  /* Primitive System Data Types */ 
#include <errno.h>      /* Errors */
#include <stdio.h>      /* Input/Output */
#include <stdlib.h>     /* General Utilities */
#include <pthread.h>    /* POSIX Threads */
#include <string.h>     /* String handling */
#include <math.h>
#include <sched.h>

/* prototype for thread routine */
void print_message_function ( void *ptr );
static inline void workload_for_1ms();
int job();

/* struct to hold data to be passed to a thread
   this shows how multiple data items can be passed to a thread */
typedef struct str_thdata
{
    int thread_no;
    char message[100];
    cpu_set_t mask;
} thdata;

int main()
{
    pthread_t thread1, thread2;  /* thread variables */
    thdata data1, data2;         /* structs to be passed to threads */
    CPU_ZERO(&(data1.mask));
    CPU_ZERO(&(data2.mask));
    /* initialize data to pass to thread 1 */
    data1.thread_no = 1;
    strcpy(data1.message, "Hello!");

    /* initialize data to pass to thread 2 */
    data2.thread_no = 2;
    strcpy(data2.message, "Hi!");
    
    // Bind the task to the assigned cores
    size_t i;
    for (i = 0; i <= 2; i = i + 2) {
        CPU_SET(i, &(data1.mask));
        CPU_SET(i, &(data2.mask));
    }

    /* create threads 1 and 2 */    
    pthread_create(&thread1, NULL, (void *) &print_message_function, (void *) &data1);
    pthread_create(&thread2, NULL, (void *) &print_message_function, (void *) &data2);

    /* Main block now waits for both threads to terminate, before it exits
       If main block exits, both threads exit, even if the threads have not
       finished their work */ 
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
              
    /* exit */  
    exit(0);
} /* main() */

/**
 * print_message_function is used as the start routine for the threads used
 * it accepts a void pointer 
**/
void print_message_function( void *ptr )
{
    thdata *data;            
    data = (thdata *) ptr;  /* type cast to a pointer to thdata */
    sched_setaffinity(0, sizeof(data->mask), &(data->mask));
    /* do the work */
    printf("Thread %d says %s \n", data->thread_no, data->message);
    job();

    pthread_exit(0); /* exit */
} /* print_message_function ( void *ptr ) */

static inline void workload_for_1ms()
{
    long long i;
    double temp;
    for ( i = 0; i < 190000; ++i ) {
        temp = sqrt((double)i*i);
        sqrt(temp);
    }     
}

int job()
{
    int i = 0, wcet = 2000;
    /* Do real-time calculation. */
    for ( i = 0; i < wcet; ++i ) {
      workload_for_1ms();
    }
    return 0; 
}
