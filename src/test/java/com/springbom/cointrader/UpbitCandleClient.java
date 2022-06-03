package com.springbom.cointrader;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class UpbitCandleClient {

    private final RestTemplate restTemplate;

    public UpbitCandleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MinuteCandle> getMinuteCandles(MinuteType minuteType, MarketType marketType, int count, LocalDateTime to) {
        URI uri = getUri(minuteType, marketType, count, to);
        ResponseEntity<List<MinuteCandle>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<MinuteCandle>>() {});
        return response.getBody();
    }

    private URI getUri(MinuteType minuteType, MarketType marketType, int count, LocalDateTime to) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.upbit.com")
                .path("/v1/candles/minutes/{minute}")
                .queryParam("market", marketType.getUrlValue())
                .queryParam("count", count)
                .queryParam("to", to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .buildAndExpand(minuteType.getMinute())
                .toUri();
    }
}