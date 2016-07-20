// Each real time task should be compiled as a separate program and include task_manager.cpp and task.h
// in compilation. The task struct declared in task.h must be defined by the real time task.

#include <iostream>
#include "task.h"

int main(int argc, char *argv[])
{
	// Initialize the task
	if (task.init != NULL)
		task.init();
    
    // Run the task
    if (task.init != NULL)
		task.run();
    
	// Finalize the task
	if (task.finalize != NULL)
		task.finalize();

	return 0;
}

