package io.confluent.examples.connectors.stackoverflow.source;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StackOverFlowApi {

    private RestTemplate restTemplate;

    public StackOverFlowApi() {
        ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        restTemplate = new RestTemplate(clientHttpRequestFactory);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
    }

    public List<ResponseItem> getAnswers(long fromDate) {
        log.info("Retrieving answers starting on " + fromDate);
        String url = String.format("https://api.stackexchange.com/2.2/answers?fromdate=%d&order=desc&sort=creation&site=stackoverflow", fromDate);
        StackOverflowResponse answer = restTemplate.getForEntity(url, StackOverflowResponse.class).getBody();
        if (answer == null) {
            throw new RuntimeException("Did not get a valid result from SO api.");
        }
        return answer.items;
    }

    public List<ResponseItem> getAnswers() {
        return this.getAnswers((System.currentTimeMillis() / 1000 - 60 * 5));
    }

}

