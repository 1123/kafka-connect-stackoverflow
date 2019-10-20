FROM confluentinc/cp-kafka-connect:latest

RUN apt-get update && apt-get install jq unzip
COPY kafka-connect-stackoverflow-0.1.1-bin.zip /usr/share/java/
RUN cd /usr/share/java && unzip kafka-connect-stackoverflow-0.1.1-bin.zip && mv kafka-connect-stackoverflow-0.1.1 kafka-connect-stackoverflow

