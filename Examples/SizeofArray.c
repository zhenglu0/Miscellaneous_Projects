/*
You cannot calculate the size of an int array when all you've got is an int pointer.

The only way to make this "function-like" is to define a macro:

#define ARRAY_SIZE( array ) sizeof( array ) / sizeof( array[0] )
This comes with all the usual caveats of macros, of course.

Edit: (The comments below really belong into the answer...)

You cannot determine the number of elements initialized within an array, 
unless you initialize all elements to an "invalid" value first and doing 
the counting of "valid" values manually. 
If your array has been defined as having 8 elements, for the compiler it has 8 elements,
 no matter whether you initialized only 5 of them.
You cannot determine the size of an array within a function to which that array has been passed as parameter. 
Not directly, not through a macro,
 not in any way. You can only determine the size of an array in the scope it has been declared in.
The impossibility of determining the size of the array in a called function can be understood once you 
realize that sizeof() is a compile-time operator. 
It might look like a run-time function call, but it isn't: Thecompiler determines the size of the operands, 
and inserts them as constants.

In the scope the array is declared, the compiler has the information that it is actually an array, 
and how many elements it has.

In a function to which the array is passed, all the compiler sees is a pointer. 
(Consider that the function might be called with many different arrays, 
and remember that sizeof() is a compile-time operator.
*/


#include <stdio.h>
#include <stdlib.h>

void BinarySearch(int* array, int lower, int upper, int target)
{
    int size = sizeof(array)/sizeof(*array);
    printf("%d \n",size);
}

int main()
{
    int a[] = {0,1,2,3,4,5,6,7,8,9};
    //int b = BinarySearch(a,0,9,9);
    int size = sizeof(a)/sizeof(*a);
    printf("%d \n",size);
    /*
    int size = sizeof(a)/sizeof(*a);
    printf("%d \n",size);
    printf("%d \n",b);
    */

    return 0;
}
