/*
 * This is a simple C program to demonstrate the usage of callbacks
 * The callback function is in the same file as the calling code.
 * The callback function can later be put into external library like
 * e.g. a shared object to increase flexibility.
 *
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct _MyMsg {
        int appId;
        char msgbody[32];
} MyMsg;

void myfunc(MyMsg *msg)
{
        if (strlen(msg->msgbody) > 0 )
                printf("App Id = %d \nMsg = %s \n",msg->appId, msg->msgbody);
        else
                printf("App Id = %d \nMsg = No Msg\n",msg->appId);
}

/*
 * Prototype declaration
 */
void (*callback)(MyMsg *);

int main(void)
{
        MyMsg msg1;
        msg1.appId = 100;
        strcpy(msg1.msgbody, "This is a test\n");

        /*
         * Assign the address of the function 'myfunc' to the function
         * pointer 'callback'
         */
        callback = myfunc;

        /*
         * Call the function
         */
        callback((MyMsg*)&msg1);

        return 0;
}
