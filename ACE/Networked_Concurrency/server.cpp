#include <stdio.h>
#include "constants.h"
#include <ace/SOCK_Acceptor.h>
#include <ace/Time_Value.h>

int main(int argc, char *argv[])
{
	int retVal = SUCCESS;
	if (argc > 1) {
		printf("%s", "Command-line arguments passed!\n");
		retVal = ERROR;
	} else {
		printf("%s\n", argv[0]);
	}
	if (retVal == SUCCESS) {
		ACE_INET_Addr server(SERVER_PORT);
		ACE_SOCK_Acceptor client_responder(server);
		ACE_SOCK_Stream client_stream;
		ACE_Time_Value timeout(1000);
		ACE_INET_Addr client;
		if (client_responder.accept(client_stream, &client, &timeout) < 0) {
			printf("%s", "Connection not established with client!\n");
			retVal = ERROR;
		} else {
			printf("Client details: Host Name: %s Port Number: %d \n",
                    client.get_host_name(), client.get_port_number());
			while (true) {
				char buf[128];
				int receiveStatus = client_stream.recv(buf, sizeof(buf) - 1);
				if (receiveStatus > 0) {
					buf[receiveStatus] = '\0';
					printf("Got something: %s\n", buf);
                } else if (receiveStatus != 0) {
					printf("%s", "Network problem or incorrect type\n");
					retVal = ERROR;
					break;
                }
            }
        }
    }
	return(retVal);
}

