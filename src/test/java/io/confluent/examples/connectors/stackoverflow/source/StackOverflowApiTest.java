package io.confluent.examples.connectors.stackoverflow.source;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class StackOverflowApiTest {

    @Test
    public void test() {
        StackOverFlowApi stackoverFlowApi = new StackOverFlowApi();
        System.err.println(stackoverFlowApi.getAnswers(1571270400));
    }

    @Test
    public void test2() {
        StackOverFlowApi stackoverFlowApi = new StackOverFlowApi();
        System.err.println(stackoverFlowApi.getAnswers());
        System.err.println(stackoverFlowApi.getAnswers().stream().map(item -> item.creationDate).collect(Collectors.toList()));
    }

}

