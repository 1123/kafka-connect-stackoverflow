package io.confluent.examples.connectors.stackoverflow.source;

import lombok.Data;

@Data
public class ResponseItem {

    Owner owner;
    int answerId;
    int questionId;
    int creationDate;
    int lastActivityDate;
    int score;
    boolean isAccepted;
}
