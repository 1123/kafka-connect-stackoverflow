package io.confluent.examples.connectors.stackoverflow.source;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTaskContext;
import org.apache.kafka.connect.storage.OffsetStorageReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StackOverflowSourceTaskContext implements SourceTaskContext {

    @Override
    public Map<String, String> configs() {
        return null;
    }

    @Override
    public OffsetStorageReader offsetStorageReader() {
        return null;
    }
}

@Slf4j
class StackOverflowSourceTaskTest {

    // @Test
    public void testPoll() throws InterruptedException {
        StackOverflowSourceTask stackOverflowSourceTask = new StackOverflowSourceTask();
        SourceTaskContext sourceTaskContext = new StackOverflowSourceTaskContext();
        stackOverflowSourceTask.initialize(sourceTaskContext);
        stackOverflowSourceTask.start(new HashMap<>());
        List<SourceRecord> result = stackOverflowSourceTask.poll();
        log.info(result.toString());
    }

}