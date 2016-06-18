//
//  main.cpp
//  Fibonacci
//
//  Created by 罗铮 on 8/31/12.
//  Copyright (c) 2012 罗铮. All rights reserved.
//

#include <iostream>

using namespace std;

// recursive solution of Fibonacci
int fib (int n)
{
    if (n == 0)
    {
        return 0;
    }
    else if (n == 1)
    {
        return 1;
    }
    else
    {
        return fib(n-1)+fib(n-2);
    }    
}

// iterative solution of Fibonacci
int fib_iterative(int n)
{
    int sum = 0;
    int previous = 1;
    int before_previous = 0;
    if (n == 0)
    {
        return 0;
    }
    else if (n == 1)
    {
        return 1;
    }
    for (int i = 2; i <=n; i++)
    {
        sum = before_previous + previous;
        before_previous = previous;
        previous = sum;
    }
    return sum;
}


int main(int argc, const char * argv[])
{

    // insert code here...
    for (int i = 0; i <= 17; i++)
    {
        cout << fib(i) << " " ;
    }
    cout << endl;
    for (int i = 0; i <= 17; i++)
    {
        cout << fib_iterative(i) << " " ;
    }
    return 0;
}

