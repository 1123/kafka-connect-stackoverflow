package io.confluent.examples.connectors.stackoverflow.source;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigValue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackOverflowSourceConnectorTest {

    @Test
    public void test() {
        Map<String, String> props = new HashMap<>();
        props.put("start_time", "5");
        ConfigDef defs = new StackOverflowSourceConnector().config();
        List<ConfigValue> configs = defs.validate(props);
        System.err.println(configs);
    }
}

