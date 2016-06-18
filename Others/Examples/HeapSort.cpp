//
//  main.cpp
//  HeapSort
//
//  Created by 罗铮 on 9/4/12.
//  Copyright (c) 2012 罗铮. All rights reserved.
//

#include <iostream>
#include <vector>

using namespace std;

void insert(vector<int> &array, int x)
{
    array.push_back(x);
    int hole = array.size()-1;
    //percolate up
    for (; hole>1 && x>array[hole/2]; hole/=2)
        array[hole] = array[hole/2];
    array[hole]= x;
}

void heapSort(vector<int> &array)
{
    int last_element_index = array.size()-1;
    while (true) {
        // check whether the heap is empty
        if(last_element_index != 0){
            int tmp = array[last_element_index];
            //create a hole at root
            int hole = 1;
            array[last_element_index] = array[hole];
            int currentSize = last_element_index;
            int child;
            for( ; hole*2 < currentSize; hole=child){ //identify child position
                child = hole*2;
                //compare left and right child, select the bigger one
                if (child < (currentSize-1) && array[child+1] > array[child]){
                     ++child;
                }
                if(array[child]>tmp){
                    array[hole] = array[child]; //bubble down if child is bigger
                } //compare the smaller child with tmp
                else {
                    break; //bubble stops movement
                }
            }
            array[hole] = tmp; //fill the hole
            --last_element_index;
        } else {
            break;
        }
    }
}

int main(int argc, const char * argv[])
{
    // build the heap
    vector<int> array;
    vector<int> heap;
    array.push_back(0);
    array.push_back(20);
    array.push_back(18);
    array.push_back(16);
    array.push_back(4);
    array.push_back(17);
    array.push_back(14);
    array.push_back(8);
    array.push_back(7);
    array.push_back(9);
    array.push_back(88);
    array.push_back(66);
    array.push_back(22);
    array.push_back(33);
    array.push_back(99);
    array.push_back(100);
    cout << "The unsorted array:" << endl;
    //print the array
    for ( vector<int>::iterator it = array.begin() ; it < array.end(); it++ ){
        cout << " " << *it;
        // build the heap
        insert(heap, *it);
    }
    // print the heap
    cout << endl << "The built heap:" << endl;
    for ( vector<int>::iterator it = heap.begin() ; it < heap.end(); it++ ){
        cout << " " << *it;
    }
    cout << endl << "The sorted array:" << endl;
    // print the sorted array
    heapSort(heap);
    for ( vector<int>::iterator it = heap.begin() ; it < heap.end(); it++ ){
        cout << " " << *it;
    }
    cout << endl;
    return 0;
}

