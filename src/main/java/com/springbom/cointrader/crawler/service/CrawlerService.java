package com.springbom.cointrader.crawler.service;

import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.enums.MinuteType;

import java.time.LocalDateTime;

public interface CrawlerService {

    void saveMinuteCandlesByPeriod(MinuteType minuteType, MarketType marketType, LocalDateTime from, LocalDateTime to);
}
