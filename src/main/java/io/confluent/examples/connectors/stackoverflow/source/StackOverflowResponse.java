package io.confluent.examples.connectors.stackoverflow.source;

import lombok.Data;

import java.util.List;

@Data
public class StackOverflowResponse {

    List<ResponseItem> items;
    boolean hasMore;
    int quotaMax;
    int quotaRemaining;

}
