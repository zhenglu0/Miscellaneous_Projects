#!/bin/bash
a=23
b=a
c=$a
echo a b c d
echo $a $b $c $d
echo "$a $b $c $d"
echo '$a $b $c $d'
command1=pwd
echo "$command1"
command2=`pwd`
echo "$command2"