// A simple matrix-vector multiplication task that uses OpenMP
// Call the function as follows: executable_name num_rows num_cols

//SG
#include <iostream>
#include "task.h"

using namespace std;

int init()
{
	cout << "init !!!" << endl;
	return 0;
}

int run()
{
    cout << "run !!!" << endl;
	return 0;
}

int finalize()
{
    cout << "finalize !!!" << endl;
	return 0;
}

task_t task = { init, run, finalize };

