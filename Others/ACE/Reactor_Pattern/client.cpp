#include "client.h"

Client::Client(unsigned short port, const char *ipAddr, vector<string> input) : 
	address(port, ipAddr), args(input)
{
}

int Client::handle_timeout(const ACE_Time_Value &currentTime, const void *act)
{
	if (this->connector.connect(this->stream, this->address) >= 0)
	{
		for (string &arg : this->args)
		{
			if (this->stream.send_n(arg.c_str(), arg.size()) < 0)
			{
				this->stream.close();
				cerr << "Stream failed to send" << endl;
				return -1;
			}

			if (this->stream.send_n(" ", 1) < 0)
			{
				this->stream.close();
				cerr << "Stream failed to send delimiter" << endl;
				return -1;
			}
		}

		this->stream.close();
	}
	else
	{
		cerr << "Connector failed to connect" << endl;
		return -1;
	}

	return 0;
}
