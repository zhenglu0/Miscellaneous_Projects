// Each real time task should be compiled as a separate program and include task_manager.cpp and task.h
// in compilation. The task struct declared in task.h must be defined by the real time task.

#include "task.h"

int main(int argc, char *argv[])
{
	// Initialize the task
	if (task.init)
		task.init(argc, argv);
    
    // Run the task
    if (task.init)
		task.run(argc, argv);
    
	// Finalize the task
	if (task.finalize)
		task.finalize(argc, argv);

	return 0;
}

