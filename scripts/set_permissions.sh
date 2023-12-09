#!/bin/bash

SCRIPT_DIR="/home/ec2-user/scripts"

if [ ! -d "$SCRIPT_DIR" ]; then
    mkdir -p "$SCRIPT_DIR"
fi

chmod +x "$SCRIPT_DIR/stop_server.sh"
chmod +x "$SCRIPT_DIR/start_server.sh"
