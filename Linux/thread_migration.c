//#define _GNU_SOURCE
#include <pthread.h>    /* POSIX Threads */
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <errno.h>
#include <sched.h>

#define handle_error_en(en, msg) \
    do { errno = en; perror(msg); exit(EXIT_FAILURE); } while (0)

int main(int argc, char *argv[])
{
    int s, j;
    cpu_set_t cpuset;
    pthread_t thread;
    thread = pthread_self();

    /* Generate secret number between 0 and max cpu id: */
    int max_number_cores = 4;
    /* Set affinity mask to include CPUs 0 to 7 */
    CPU_ZERO(&cpuset);
    for (j = 0; j < max_number_cores; j++)
        CPU_SET(j, &cpuset);

    s = pthread_setaffinity_np(thread, sizeof(cpu_set_t), &cpuset);
    if (s != 0)
        handle_error_en(s, "pthread_setaffinity_np");
    
    /* Check the actual affinity mask assigned to the thread */
    s = pthread_getaffinity_np(thread, sizeof(cpu_set_t), &cpuset);
    if (s != 0)
        handle_error_en(s, "pthread_getaffinity_np");

    for (j = 0; j < CPU_SETSIZE; j++)
        if (CPU_ISSET(j, &cpuset))
            printf("CPU %d\n", j);

    int current_cpu_id = sched_getcpu();    
    printf("Current CPU ID   = %d\n\n", current_cpu_id);

    /* Initialize random seed: */
    srand (time(NULL));

    /* Randomly choose other cores */
    int migrated_to_cpu_id = rand() % max_number_cores;
    
    /* Make sure the thread will migrate to a different core*/
    if (migrated_to_cpu_id == current_cpu_id) {
        if (current_cpu_id == max_number_cores)
            migrated_to_cpu_id--;
        else
            migrated_to_cpu_id++;
    }
    printf("Migratedto CPU ID   = %d\n", migrated_to_cpu_id);

    /* Set affinity mask to include CPUs 0 to 7 */
    CPU_ZERO(&cpuset);
    CPU_SET(migrated_to_cpu_id, &cpuset);

    s = pthread_setaffinity_np(thread, sizeof(cpu_set_t), &cpuset);
    if (s != 0)
        handle_error_en(s, "pthread_getaffinity_np");

    for (j = 0; j < CPU_SETSIZE; j++)
        if (CPU_ISSET(j, &cpuset))
            printf("CPU %d\n", j);

    current_cpu_id = sched_getcpu();    
    printf("Current CPU ID   = %d\n", current_cpu_id);

    exit(EXIT_SUCCESS);           
}
