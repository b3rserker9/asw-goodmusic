#!/bin/bash

# inizializza il db delle recensioni-seguite
echo Starting recensioni-seguite db in docker container

docker run  --name recensioniseguite -p 5434:5432 -e POSTGRES_PASSWORD=postgres -d postgres

