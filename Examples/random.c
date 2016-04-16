/* rand example: guess the number */
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */

int main ()
{
    int iSecret, iGuess;

    /* initialize random seed: */
    srand (time(NULL));

    /* generate secret number between 1 and 10: */
    iSecret = rand() % 10 + 1;
    printf("iSecret = %d\n", iSecret);

    do {
        printf ("Guess the number (1 to 10): ");
        scanf ("%d",&iGuess);
        if (iSecret<iGuess) puts ("The secret number is lower");
        else if (iSecret>iGuess) puts ("The secret number is higher");
    } while (iSecret!=iGuess);
    
    puts ("Congratulations!");
    return 0;
}
