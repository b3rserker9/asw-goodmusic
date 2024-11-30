#!/bin/bash

# inizializza il db delle recensioni-seguite
echo Starting connessioni db in docker container

docker run  --name connessioni -p 5434:5432 -e POSTGRES_PASSWORD=postgres -d postgres

