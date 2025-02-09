#!/bin/bash

echo Halting GOODMUSIC   

docker compose down -v
sleep 4
#jps
docker ps
echo Stopping GOODMUSIC completed
