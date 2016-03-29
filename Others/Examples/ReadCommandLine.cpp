//
//  ReadCommandLine.cpp
//
//  Created by 罗铮 on 11/4/12.
//  Copyright (c) 2012 罗铮. All rights reserved.
//

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

int main(int argc, const char * argv[])
{
    int a, b;

    for (int i = 1; i < argc-1; i++)
    {
        string s(argv[i]) ;
        istringstream iss(s);
        if(iss >> a)
            cout << "a is assigned" << endl;

        string s2(argv[++i]) ;
        istringstream iss2(s2);
        if(iss2 >> b)
            cout << "b is assigned" << endl;
    }

    cout << "The value of a and b is " 
         << a << " " << b << endl;

    int i;
    stringstream sstr("1 2 3 4 5");
    while(sstr >> i)
    {
        cout << i << endl;
        if(sstr.eof())
        {
            std::cout << "eof" << std::endl;
        }
    }

}

