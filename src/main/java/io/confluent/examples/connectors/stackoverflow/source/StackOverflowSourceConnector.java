package io.confluent.examples.connectors.stackoverflow.source;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.*;

public class StackOverflowSourceConnector extends SourceConnector {

    @Override
    public void start(Map<String, String> map) {

    }

    @Override
    public Class<? extends Task> taskClass() {
        return StackOverflowSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int i) {
        Map<String, String> taskConfig = new HashMap<>();
        return Collections.singletonList(taskConfig);
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        ConfigDef configDef = new ConfigDef();
        configDef.define(
                "start_time",
                Type.LONG,
                0L,
                null,
                ConfigDef.Importance.HIGH,
                "Configuration with default value."
        );
        return configDef;
    }

    @Override
    public String version() {
        return "0.0.1";
    }
}

