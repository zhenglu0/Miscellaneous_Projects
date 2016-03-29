#include <stdio.h>

int  main()
{

    int i = 0;
    //int b = (i++) + (++i);
    //int b = (++i) + (i++);
    //int b = i++;
    int b = ++i;
    printf("%d\n", b);
/*
    i = 1;
    int a = (i++);
    printf("%d\n", a);
*/
    //int i = 0;
    //i = (i++) + (++i);
    //(i++) + (++i);
    //printf("%d\n", i);

    //int i = 1;
    //++i;
    //i = (i++);
    //printf("%d\n", i);


    //int i = 1;
    //++i;
    //i = (++i);
    //printf("%d\n", i);

    /*
    volatile int u = 0;
    u = u++ + ++u;
    printf("%d\n", u); // 1

    u = 1;
    u = (u++);
    printf("%d\n", u); // 2 Should also be one, no ?

    register int v = 0;
    v = v++ + ++v;
    printf("%d\n", v); // 3 (Should be the same as u ?)
    */

    return 0;
}

