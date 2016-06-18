#include <stdio.h>

int test(int a)
{
    a = 5;
    return a;
    //printf("%d\n",a);
}

int  main()
{
    //int i;
    /*
    for(i = 0; i < 100; i++)
    {
        printf("%d\n", i);
    }*/
    int i = 11;
    //int j = 2;
    //int k = 0;
    i = test(i);
    //int j = 2;
    //int k = i + j;
    //printf("%d\n",k);
    //printf("we are now outside of the loop. yay.\n");
    return 0;
 }
