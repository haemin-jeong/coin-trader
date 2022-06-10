package com.springbom.cointrader.client;

import com.springbom.cointrader.crawler.dto.FiveMinuteCandleResponse;
import com.springbom.cointrader.crawler.entity.FiveMinuteCandle;
import com.springbom.cointrader.crawler.repository.FiveMinuteCandleRepository;
import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.util.MinuteCandleConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class UpbitCandleClientTest {

    @Autowired
    UpbitCandleClient upbitCandleClient;

    @Autowired
    FiveMinuteCandleRepository minuteCandleRepository;

    @Test
    @DisplayName("비트코인 5분봉 가져오기 테스트")
    void getMinuteCandlesByCountTest() {
        List<FiveMinuteCandleResponse> minuteCandles = upbitCandleClient.getFiveMinuteCandlesByCount(MarketType.KRW_BTC, 20, LocalDateTime.now());
        assertThat(minuteCandles.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("22.5.1 ~ 22.5.31 기간의 비트코인 5분봉 크롤링해서 DB에 저장하기")
    void getMinuteCandlesByPeriodTest() {
        List<FiveMinuteCandleResponse> minuteCandles = upbitCandleClient.getFiveMinuteCandlesByPeriod(MarketType.KRW_BTC, LocalDateTime.of(2022, 5, 1, 0, 0), LocalDateTime.of(2022, 5, 31, 23, 56));
        List<FiveMinuteCandle> candleEntities = minuteCandles.stream().map(MinuteCandleConverter::convertFiveMinuteCandle).collect(Collectors.toList());
        minuteCandleRepository.saveAll(candleEntities);
    }
}
