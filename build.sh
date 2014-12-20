#!/bin/bash

# Convenient script for building and testing
# author: wanghongfei
# 2014.12.18


if [ "$1" == "help" ]; then
    echo "example:"
    echo "    running test only--> build.sh test"
    echo "    build web module only--> build.sh web"
    echo "    build service module only--> build.sh service"
    echo "    build only--> build.sh"

    exit 0
fi

declare PARAM=""

if [ "$1" == "test" ]; then # test all modules
    echo ">>> Running test for all modules"
    PARAM="test"
elif [ "$1" == "" ]; then # build all modules
    echo ">>> Building all modules"
    PARAM="clean install -Dmaven.test.skip=true"
elif [ "$1" == "web" ]; then # build web module only
    echo ">>> build web module only"
    cd hello-web
    PARAM="clean install -Dmaven.test.skip=true"
elif [ "$1" == "service" ]; then # build service module only
    echo ">>> build service module only"
    cd hello-service
    PARAM="clean install -Dmaven.test.skip=true"
else
    echo "invalid parameter $1"
    exit 0
fi

echo "Executing command : mvn $PARAM"

mvn $PARAM
