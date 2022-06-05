package com.springbom.cointrader;

import com.springbom.cointrader.crawler.dto.MinuteCandleResponse;
import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.enums.MinuteType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpbitCandleClient {

    private static final int MAX_REQUEST_COUNT = 200;
    private final RestTemplate restTemplate;

    public UpbitCandleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MinuteCandleResponse> getMinuteCandlesByCount(MinuteType minuteType, MarketType marketType, int count, LocalDateTime to) {
        URI uri = getUri(minuteType, marketType, count, to);
        ResponseEntity<List<MinuteCandleResponse>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<MinuteCandleResponse>>() {});
        return response.getBody();
    }

    public List<MinuteCandleResponse> getMinuteCandlesByPeriod(MinuteType minuteType, MarketType marketType, LocalDateTime from, LocalDateTime to) {
        List<MinuteCandleResponse> results = new ArrayList<>();
        LocalDateTime currentTime = to;

        while (currentTime.isAfter(from)) {
            int requestCount = getRequestCount(minuteType, from, currentTime);
            List<MinuteCandleResponse> minuteCandles = getMinuteCandlesByCount(minuteType, marketType, requestCount, currentTime);
            results.addAll(minuteCandles);

            currentTime = currentTime.minusMinutes(MAX_REQUEST_COUNT * 5);
            sleep(100L);
        }

        return results;
    }

    /**
     * current 시간과 from 시간 차를 통해 API 요청 count를 계산한다.
     */
    private int getRequestCount(MinuteType minuteType, LocalDateTime from, LocalDateTime current) {
        int requestCount = MAX_REQUEST_COUNT;
        current = current.minusMinutes(MAX_REQUEST_COUNT * minuteType.getMinute());

        if (current.isBefore(from)) {
            requestCount -= ChronoUnit.MINUTES.between(current, from) / minuteType.getMinute();
        }

        return requestCount;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private URI getUri(MinuteType minuteType, MarketType marketType, long count, LocalDateTime to) {
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