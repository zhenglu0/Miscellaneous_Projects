// CompareToMax.cpp : Defines the entry point for the console

#include <iostream>

using namespace std;

int CompareToMax(unsigned int arr[], unsigned int n)
{
	unsigned int curMax;
	if(n == 0)
	{
		return -1;
	}
	curMax = arr[0];
	for(unsigned int i = 0; i < n; ++i)
	{
		if(arr[i] > curMax)
		{
			curMax = arr[i];
		}
	}
	return curMax;
}

int main(int argc, char * argv[])
{
	unsigned int number[] = {1,3,2,4,10,6,7,8,9};
	int max = CompareToMax(number,sizeof(number)/sizeof(int));
	cout << "The biggest number in the array is " << max << endl;
	return 0;
}

