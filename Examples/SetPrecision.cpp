//
//  SetPrecision.cpp
//  
//  Created by 罗铮 on 2/16/13.
//  Copyright (c) 2013 罗铮. All rights reserved.
//

#include <iostream>

using namespace std;

int main(int argc, const char * argv[])
{
    double f = 30.1415926535;
    cout.unsetf(ios::floatfield);            // floatfield not set
    cout.precision(3);
    cout << f << endl;
    cout.precision(10);
    cout << f << endl;
    cout.setf(ios::fixed,ios::floatfield);   // floatfield set to fixed
    cout << f << endl;
    cout.setf(ios::fixed); // floatfield set to fixed
    cout.precision(2);
    cout << f << endl;
    
    return 0;
}
