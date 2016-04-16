#!/bin/bash
echo " $0: The first three paparemeters are $1, $2, $3"
shift 2
echo " $0: The first three paparemeters are $1, $2, $3"
set -- `ls –l $0`
echo " $0: The first three paparemeters are $1, $2, $3"
# run this script with ./shell_parameters.sh 1 2 3 4 5 6 7 8 9