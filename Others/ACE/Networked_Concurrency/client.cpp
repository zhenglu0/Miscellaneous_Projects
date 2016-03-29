#include <stdio.h>
#include <sstream>
#include <ace/SOCK_Connector.h>
#include "constants.h"

int main(int argc, char *argv[])
{
	int retVal = SUCCESS;
	if (argc <= 1) {
        printf("%s", "No command-line arguments passed!\n");
		retVal = ERROR;
    } else {
		printf("%s\n", argv[0]);
    }
	if (retVal == SUCCESS) {
		ACE_INET_Addr server(SERVER_PORT);
		ACE_SOCK_Stream client_stream;
		ACE_SOCK_Connector client_connector;
		if (client_connector.connect(client_stream, server) < SUCCESS) {
			printf("%s", "Failure to establish connection!\n");
			retVal = ERROR;
        }
		for(int index = 1; index < argc && retVal == SUCCESS; index++) {
			std::string argumentWithoutSpace(argv[index]);
			std::string argumentWithSpace;
			std::stringstream stringManipulator;
			stringManipulator << argumentWithoutSpace << " ";
			argumentWithSpace = stringManipulator.str();
            if (client_stream.send_n(argumentWithSpace.c_str(), argumentWithSpace.length(), 0) < 0) {

				printf("%s", "Error in sending message");
				retVal = ERROR;
            }
        }
		if (client_stream.close() != SUCCESS) {
			retVal = ERROR;
        }
    }
	return(retVal);
}
