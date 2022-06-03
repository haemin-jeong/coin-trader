package com.springbom.cointrader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpbitCandleClientTest {

    @Autowired
    UpbitCandleClient upbitCandleClient;

    @Autowired
    MinuteCandleRepository minuteCandleRepository;

    @Test
    @DisplayName("비트코인 5분봉 가져오기 테스트")
    void getMinuteCandlesByCountTest() {
        List<MinuteCandleResponse> minuteCandles = upbitCandleClient.getMinuteCandlesByCount(MinuteType.FIVE, MarketType.KRW_BTC, 20, LocalDateTime.now());
        assertThat(minuteCandles.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("22.5.1 ~ 22.5.31 기간의 비트코인 5분봉 크롤링해서 DB에 저장하기")
    void getMinuteCandlesByPeriodTest() {
        List<MinuteCandleResponse> minuteCandles = upbitCandleClient.getMinuteCandlesByPeriod(MinuteType.FIVE, MarketType.KRW_BTC, LocalDateTime.of(2022, 5, 1, 0, 0), LocalDateTime.of(2022, 5, 31, 23, 56));
        List<MinuteCandle> candleEntities = minuteCandles.stream().map(MinuteCandleConverter::toEntity).collect(Collectors.toList());
        minuteCandleRepository.saveAll(candleEntities);
    }
}
