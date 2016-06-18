//
//  PrefixSum.cpp
//  
//  Created by 罗铮 on 2/24/13.
//  Copyright (c) 2013 罗铮. All rights reserved.
//

#define N 7

#include <iostream>
#include <vector>

using namespace std;

// calculate prefix sum array
int *prefixSumSerial(int x[], int n) {
    int *s = new int [n]; // calculate prefix sum
    int total = 0;
    for (int i = 0; i < n; i++) {
        total += x[i];
        s[i] = total;
    }
    return s;
}

int *prefixSumParallel(int x[], int n) {
    // base case
    int *s = new int [n];
    if (n == 1) {
        s[0] = x[0];
        return s;
    }
    // calculate sum between two pair of integers
    // it has two cases, n is even or odd
    int halfsize;
    int *y = NULL;
    if (n % 2 == 0) {
        halfsize = n/2;
        y = new int [halfsize];
        for (int i = 0; i < halfsize; i++) {
            y[i] = x[2*i] + x[2*i+1];
        }
    } else {
        halfsize = n/2 + 1;
        y = new int [halfsize];
        for (int i = 0; i < halfsize - 1; i++) {
            y[i] = x[2*i] + x[2*i+1];
        }
        y[halfsize-1] = x[n-1];
    }
    // calculate parellel prefix between two pairs
    int *z = prefixSumParallel(y, halfsize);
    delete [] y;
    // calculate the prefix sum for every index in x
    for (int i = 0; i < n; i++) {
        if (i == 0) {
            s[0] = x[0];
        } else if (i % 2 == 1) {
            s[i] = z[i/2];
        } else {
            s[i] = z[i/2-1] + x[i];
        }
    }
    delete [] z;
    return s;
}

vector <int> prefixSumParallel(vector<int> &x) {
    // base case
    int n = (int)x.size();
    vector <int> s(n);
    if (n == 1) {
        s[0] = x[0];
        return s;
    }
    // calculate sum between two pair of integers
    // it has two cases, n is even or odd
    vector <int> y;
    if (n % 2 == 0) {
        int halfsize = n/2;
        y.resize(halfsize);
        for (int i = 0; i < halfsize; i++) {
            y[i] = x[2*i] + x[2*i+1];
        }
    } else {
        int halfsize = n/2 + 1;
        y.resize(halfsize);
        for (int i = 0; i < halfsize - 1; i++) {
            y[i] = x[2*i] + x[2*i+1];
        }
        y[halfsize-1] = x[n-1];
    }
    // calculate parellel prefix between two pairs
    vector <int> z = prefixSumParallel(y);
    // calculate the prefix sum for every index in x
    for (int i = 0; i < n; i++) {
        if (i == 0) {
            s[0] = x[0];
        } else if (i % 2 == 1) {
            s[i] = z[i/2];
        } else {
            s[i] = z[i/2-1] + x[i];
        }
    }
    return s;
}

int main(int argc, const char * argv[])
{
    int size = N;
    int x[] = {1,2,3,4,5,6,7};
    
    int *prefixArrayS = prefixSumSerial(x, size);
    for (int i = 0; i < size; i++) {
        cout << prefixArrayS[i] << " ";
    }
    cout << endl;
    
    int *prefixArrayP = prefixSumParallel(x, size);
    for (int i = 0; i < size; i++) {
        cout << prefixArrayP[i] << " ";
    }
    cout << endl;
    
    vector<int> v (x,x + N);
    vector<int> prefixVector = prefixSumParallel(v);
    for (size_t i = 0; i < prefixVector.size(); i++) {
        cout << prefixVector[i] << " ";
    }
        
    return 0;
}

