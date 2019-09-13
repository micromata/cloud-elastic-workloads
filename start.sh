#!/bin/bash

url=$1
filename=numbers.txt

numbers="["$(cat $filename|tr '\n' ,| rev | cut -c 2- | rev)"]"
time http -v POST $url numbers:=$numbers
