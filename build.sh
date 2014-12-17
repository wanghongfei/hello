#!/bin/bash

# Convenient scirpt for building and testing
# author: wanghongfei
# 2014.12.18

declare PARAM="clean install -Dmaven.test.skip=true"

if [ "$1" == "help" ]; then
    echo "example:"
    echo "    running test only--> build.sh test"
    echo "    build only--> build.sh"

    exit 0
fi

if [ "$1" == "test" ]; then
    PARAM="test"
else
    echo "invalid parameter $1"
    exit 0
fi

echo "Executing command : mvn $PARAM"

mvn $PARAM
