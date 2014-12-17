#!/bin/bash

# Convenient scirpt for building and testing
# author: wanghongfei
# 2014.12.18


if [ "$1" == "help" ]; then
    echo "example:"
    echo "    running test only--> build.sh test"
    echo "    build only--> build.sh"

    exit 0
fi

declare PARAM=""

if [ "$1" == "test" ]; then
    PARAM="test"
elif [ "$1" = "" ]; then
    PARAM="clean install -Dmaven.test.skip=true"
else
    echo "invalid parameter $1"
    exit 0
fi

echo "Executing command : mvn $PARAM"

mvn $PARAM
