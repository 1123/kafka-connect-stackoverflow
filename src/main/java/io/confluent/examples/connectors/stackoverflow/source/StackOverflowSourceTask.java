package io.confluent.examples.connectors.stackoverflow.source;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class StackOverflowSourceTask extends SourceTask {

    private StackOverFlowApi stackOverFlowApi;
    private final String LAST_CREATION_DATE = "last_creation_date";
    private final String OUTPUT_TOPIC = "answers";
    private int lastCreationDateRetrieved = 0;

    @Override
    public String version() {
        return "0.0.1";
    }

    @Override
    public void start(Map<String, String> map) {
        stackOverFlowApi = new StackOverFlowApi();
    }

    private Map<String, String> sourcePartition() {
        Map<String, String> partition = new HashMap<>();
        partition.put("type", "answers");
        return partition;
    }

    private List<ResponseItem> getItems() {
        if (lastCreationDateRetrieved == 0) {
            log.info("First poll since startup of this task");
            Map<String, Object> offset =
                    this.context.offsetStorageReader().offset(sourcePartition());
            if (offset == null) {
                log.info("No offsets stored in the SourceTaskContext");
                log.info("Starting to retrieve data based upon current time");
                return stackOverFlowApi.getAnswers();
            } else {
                log.info("Retrieving data based upon last stored offset in SourceTaskContext");
                return stackOverFlowApi.getAnswers((long) offset.get(LAST_CREATION_DATE));
            }
        } else {
            log.info("Starting to retrieve data from last retrieved offset");
            return stackOverFlowApi.getAnswers(lastCreationDateRetrieved);
        }
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        log.info("Sleeping 10 seconds, then polling for answers");
        Thread.sleep(1000 * 10);
        List<ResponseItem> items = getItems();
        Map<String, Object> sourceOffset = new HashMap<>();
        sourceOffset.put(LAST_CREATION_DATE, items.get(0).creationDate);
        log.info("Found " + items.size() + " answers");
        lastCreationDateRetrieved = items.get(0).creationDate;
        return items.stream().map(
                item -> new SourceRecord(
                        sourcePartition(),
                        sourceOffset,
                        OUTPUT_TOPIC,
                        Schema.INT32_SCHEMA,
                        item.answerId
                )
        ).collect(Collectors.toList());
    }

    @Override
    public void stop() {

    }
}
