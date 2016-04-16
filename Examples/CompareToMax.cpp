// CompareToMax.cpp : Defines the entry point for the console

#include <iostream>

using namespace std;

int CompareToMax(unsigned int arr[], unsigned int n)
{
	unsigned int isMax;
	if(n == 0)
		return -1;

	for(unsigned int i = 0; i < n; ++i)
    {
        for(unsigned int j = 0; j < n; ++j)
        {
            isMax = 1;
            if(arr[j] > arr[i])
                isMax = 0;
        }
        if(isMax == 1)
            return arr[i];
    }
    return -1;
}

int main(int argc, char * argv[])
{
	unsigned int number[] = {1,3,2,4,10,6,7,8,9};
	int max = CompareToMax(number,sizeof(number)/sizeof(int));
	cout << "The biggest number in the array is " << max << endl;
	return 0;
}

