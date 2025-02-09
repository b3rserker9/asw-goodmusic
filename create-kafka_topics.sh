#!/bin/bash

echo "Create topics for kafka"

docker exec -it asw-goodmusic-kafka-1 kafka-topics.sh --bootstrap-server asw-goodmusic-kafka-1:9092 --create --topic connessione-event-createdChannel --replication-factor 1 --partitions 1
docker exec -it asw-goodmusic-kafka-1 kafka-topics.sh --bootstrap-server asw-goodmusic-kafka-1:9092 --create --topic connessione-event-deletedChannel --replication-factor 1 --partitions 1
docker exec -it asw-goodmusic-kafka-1 kafka-topics.sh --bootstrap-server asw-goodmusic-kafka-1:9092 --create --topic recensioni-event-channel --replication-factor 1 --partitions 1

sleep 2

docker exec -it asw-goodmusic-kafka-1 kafka-topics.sh --bootstrap-server asw-goodmusic-kafka-1:9092 --list

echo "Topics created"
