package com.springbom.cointrader.crawler;

import com.springbom.cointrader.crawler.service.CrawlerService;
import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.enums.MinuteType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Crawler {

    private final CrawlerService minuteCandleService;

    public Crawler(CrawlerService minuteCandleService) {
        this.minuteCandleService = minuteCandleService;
    }

    public void run() {
        minuteCandleService.saveMinuteCandlesByPeriod(MinuteType.FIVE, MarketType.KRW_BTC, LocalDateTime.of(2022, 5, 1, 0, 0), LocalDateTime.of(2022, 5, 31, 23, 56));
    }
}
