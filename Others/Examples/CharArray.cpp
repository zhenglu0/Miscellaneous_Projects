#include <stdio.h>
#include <string.h>

int charray(char test[]);

int main()
{
    char test[] = "YY love LL.";
    charray(test);
    printf("Text in %s\n", test);
    return 0;
}

int charray(char test[])
{
    /*
    test[0] = 'C';
    test[1] = 'O';
    test[2] = 'L';
    test[3] = 'O';
    test[4] = 'R';
    test[5] = '\0';
    */
    //strncpy("YY_Test",test,strlen("YY_Test"));
    strncpy(test,"YY_Test",strlen("YY_Test")+1);
    return 0;
}
