package com.springbom.cointrader;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UpbitCandleClient {

    private final RestTemplate restTemplate;

    public UpbitCandleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MinuteCandle> getMinuteCandles(MinuteType minuteType, MarketType marketType, int count, LocalDateTime to) {
        String requestUrl = UrlBuilder.buildMinuteCandleUrl(minuteType.getMinute(), marketType, count, to);
        ResponseEntity<List<MinuteCandle>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<MinuteCandle>>() {});
        return response.getBody();
    }
}
