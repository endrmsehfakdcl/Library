#!/bin/bash

nohup java -jar /home/ec2-user/Library-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

echo "Application started."
