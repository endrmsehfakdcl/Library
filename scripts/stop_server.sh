#!/bin/bash

PID=$(ps -ef | grep Library-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')

if [ -z "$PID" ]
then
    echo "Application is already stopped."
else
    echo "Stopping application with PID $PID."
    kill $PID
fi
