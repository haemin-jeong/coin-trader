package com.springbom.cointrader;

import java.time.LocalDateTime;

public interface CrawlerService {

    void saveMinuteCandlesByPeriod(MinuteType minuteType, MarketType marketType, LocalDateTime from, LocalDateTime to);
}
