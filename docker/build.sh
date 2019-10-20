#!/usr/bin/env bash

cp ../target/kafka-connect-stackoverflow-0.1.1-bin.zip .
gcloud builds submit --tag gcr.io/stoked-duality-228123/connect-stackoverflow:v14 .

# Check the status: curl localhost:8083/connectors/sample-connector/status | jq .
