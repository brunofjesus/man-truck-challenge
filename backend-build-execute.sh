#!/bin/bash

cd "${0%/*}"
chmod +x mvnw
./mvnw package

java -jar ./web/target/web-0.0.1-SNAPSHOT.jar
