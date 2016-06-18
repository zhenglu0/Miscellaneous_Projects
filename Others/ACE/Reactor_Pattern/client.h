#include <iostream>
#include <ace/ACE.h>
#include <ace/INET_Addr.h>
#include <ace/SOCK_Connector.h>
#include <ace/Event_Handler.h>
#include <ace/Time_Value.h>
#include <string>
#include <vector>

using namespace std;

class Client : public ACE_Event_Handler
{
	ACE_INET_Addr address;
	ACE_SOCK_Connector connector;
	ACE_SOCK_Stream stream;
	vector<string> args;

public:
	virtual int handle_timeout(const ACE_Time_Value &currentTime, const void *act = 0);
	Client(unsigned short port, const char *ipAddr, vector<string> input);
}; 
