#include "client.h"
#include <ace/Timer_Queue_Adapters.h>
#include <ace/Timer_Heap.h>
#include <ace/OS.h>
#include <ace/Reactor.h>

using namespace std;

enum ErrorCodes
{
	SUCCESS,
	ARG_ERROR,
	CONNECT_ERROR,
	SEND_ERROR,
	DELIM_ERROR
};

int main(int argc, char* argv[])
{
	if (argc < 2)
	{
		cerr << "You must provide at least one argument." << endl;
		return ARG_ERROR;
	}

	unsigned short port = 2048;
	const char *ipAddr = ACE_LOCALHOST;
	vector<string> args;

	for (int i = 1; i < argc; ++i)
	{
		args.push_back(string(argv[i]));
	}

	Client client(port, ipAddr, args);

	//ACE_Thread_Timer_Queue_Adapter<ACE_Timer_Heap> timer;
	//timer.activate();

	ACE_Reactor* reactor = ACE_Reactor::instance();

	ACE_Time_Value time(3, 0);

	reactor->schedule_timer(&client, 0, time, time);

	reactor->run_reactor_event_loop();

	return SUCCESS;
}

