package com.springbom.cointrader.crawler.service;

import com.springbom.cointrader.enums.MarketType;

import java.time.LocalDateTime;

public interface CrawlerService {

    void saveFiveMinuteCandlesByPeriod(MarketType marketType, LocalDateTime from, LocalDateTime to);
}
