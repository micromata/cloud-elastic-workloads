#!/bin/bash

if [ -z "$1" ] 
then
    echo "$0 <URL to endpoint>"
    exit
fi

url=$1
filename=numbers.txt

numbers="["$(cat $filename|tr '\n' ,| rev | cut -c 2- | rev)"]"
time http --timeout 60000 -v POST $url numbers:=$numbers
