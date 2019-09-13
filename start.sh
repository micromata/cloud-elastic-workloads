#!/bin/bash

url=$1
filename=numbers.txt

numbers="["$(cat $filename|tr '\n' ,| rev | cut -c 2- | rev)"]"
time http --timeout 60000 -v POST $url numbers:=$numbers
