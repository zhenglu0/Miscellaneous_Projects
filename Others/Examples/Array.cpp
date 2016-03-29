#include <stdio.h>
#include <string.h>

#define ARRAY_SIZE(A)      (sizeof(A)/sizeof(A[0]))

int charray(int test[])
{
    test[0] = 88;
    test[5] = 77;
    return 0;
}

void array_size()
{
    int test[] = {1,2,3,4,5,6,7,8,9};
    printf("%lu \n",sizeof(test)/sizeof(int));
    printf("%lu \n",ARRAY_SIZE(test));
}

int main()
{
    int test[] = {1,2,3,4,5,6,7,8,9};
    charray(test);
    printf("%lu \n",sizeof(test)/sizeof(int));
    printf("%lu \n",ARRAY_SIZE(test));

    array_size();
    int ll[9];
    
    memcpy (ll,test,sizeof(test));
    for(int i = 0; i < 9; ++i)
        printf("%d ",ll[i]);

    printf("\n");
    return 0;
}
