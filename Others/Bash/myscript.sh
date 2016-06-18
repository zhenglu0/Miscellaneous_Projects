#!/bin/bash

#for username in $@; do
#  grep user=$username lab1a_input.txt | cut -d '"' -f2 
#done

echo "Host                               Status"
echo "-----------------------------------------------------------------------"

up_count=0
down_count=0

while read url
do
  curl $url>/dev/null 2>&1
  result=$?
  
  if [ $result -eq 0 ]
  then
    result="OK"
    ((up_count++))
  else
    result="Down"
    ((down_count++))
  fi    
  
  printf "%-35s%s\n" "$url" "$result"
done

printf "\n%-12s%s\n" "Total Up:" $up_count
printf "%-12s%s\n" "Total Down" $down_count
