package com.springbom.cointrader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UpbitCandleClientTest {

    @Autowired
    UpbitCandleClient upbitCandleClient;

    @Test
    void test() {
        List<MinuteCandle> minuteCandles = upbitCandleClient.getMinuteCandles(MinuteType.FIVE, MarketType.KRW_BTC, 20, LocalDateTime.now());
        assertThat(minuteCandles.size()).isEqualTo(20);
    }
}
