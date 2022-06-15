package com.springbom.cointrader.client;

import com.springbom.cointrader.config.SlackKeyProperties;
import com.springbom.cointrader.util.UpbitTokenGenerator.SlackTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SlackClient {

    private final RestTemplate restTemplate;
    private final SlackTokenGenerator generator;
    private final SlackKeyProperties properties;

    public void sendMessage(String message) {
        HttpHeaders headers = getHttpHeaders();
        URI uri = getUri(message);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(uri, HttpMethod.POST, entity, Void.class);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Authorization", generator.generate());
        return headers;
    }

    private URI getUri(String message) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("slack.com")
                .path("/api/chat.postMessage")
                .queryParam("channel", properties.getChannel())
                .queryParam("text", message)
                .build().toUri();
    }
}
