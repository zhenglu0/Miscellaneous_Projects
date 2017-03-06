#!/bin/bash

# Define enum type.
OPTION_STATES=(OPTION_ONE OPTION_TWO OPTION_THREE)
DEBUG="true"

count=${#OPTION_STATES[@]}
for ((i=0; $i < $count; i++)); do
    name=${OPTION_STATES[$i]}
    declare -r ${name}=$i
    
    if [[ $DEBUG == true ]]; then
      #echo $name $i
      echo ${name}
    fi
done

# Create instance of enum type, yes this is a hash table.
OPTIONS=()

# Put an element into the instance.
OPTIONS[$OPTION_ONE]=$OPTION_ONE

# Check if that element is in the instance.
if [[ ${OPTIONS[$OPTION_ONE]} == $OPTION_ONE ]]; then
  echo "woo hoo"
fi

OK_INDEX=0
CANCEL_INDEX=1
ERROR_INDEX=2
CONFIRM_INDEX=3
SAVE_INDEX=4
EXIT_INDEX=5
declare -a messageList=("ok" 
                        "cancel" 
                        "error" 
                        "confirm"
                        "save"
                        "exit")

printf "%s \n" ${messageList[$CANCEL_INDEX]}

ENUM=(OK_INDEX CANCEL_INDEX ERROR_INDEX CONFIRM_INDEX SAVE_INDEX EXIT_INDEX)

maxArg=${#ENUM[@]}

for ((i=0; i < $maxArg; i++)); do
    name=${ENUM[i]}
    declare -r ${name}=$i
done

printf "%s \n" ${messageList[$OK_INDEX]}

val="hello|beautiful|world" # assume this string comes from a database query
read a b c <<< $( echo ${val} | awk -F"|" '{print $1" "$2" "$3}' )

echo $a #hello
echo $b #beautiful
echo $c #world

val="hello|beautiful|world
a|b|c" # assume this string comes from a database query
echo ${val}
echo "${val}"

echo "${val}" | awk -F"|" '{print $1"+"$2"-"$3}'

echo "${val}" | awk -F"|" '{print $'"$CANCEL_INDEX"'}'

echo "${val}" | awk -F"|" 'BEGIN{OFS="|";} {print $'$CANCEL_INDEX' , $'$ERROR_INDEX'}'

echo "${val}" | awk -v cancel=$CANCEL_INDEX -v error=$ERROR_INDEX \
-F"|" 'BEGIN{OFS="|";} {print $cancel " + " $error}'
