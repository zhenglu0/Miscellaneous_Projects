#!/bin/bash
add()
{
    return `expr $1 + $2`
}
add $1 $3
var1=$?
add $2 $4
var2=$?
echo " $var1"
echo " $var2"
#run with ./func_quiz.sh 1 2 3 4