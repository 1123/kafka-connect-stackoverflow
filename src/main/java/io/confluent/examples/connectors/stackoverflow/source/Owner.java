package io.confluent.examples.connectors.stackoverflow.source;

import lombok.Data;

@Data
public class Owner {
    int reputation;
    int userId;
    String userType;
    int acceptRate;
    String profileImage;
    String displayName;
    String link;
}
