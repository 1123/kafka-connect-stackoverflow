#!/bin/bash

curl localhost:8083/connectors \
     -H "Accept:application/json" -H  "Content-Type:application/json" \
     -X POST \
     -d '{
  "name" : "so-connector",
    "config": {
     "connector.class" : "io.confluent.examples.connectors.stackoverflow.source.StackOverflowSourceConnector",
     "start_time" : "10000",
     "tasks.max" : "1"
   }
}'

curl localhost:8083/connector-plugins | jq .