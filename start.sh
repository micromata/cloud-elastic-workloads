#!/bin/bash

url=$1
filename=n

numbers="["$(cat $filename|tr '\n' ,| rev | cut -c 2- | rev)"]"
time http -v POST $url numbers:=$numbers
