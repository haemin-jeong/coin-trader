package com.springbom.cointrader.common.minutecandle.service;

import com.springbom.cointrader.common.minutecandle.entity.FiveMinuteCandle;
import com.springbom.cointrader.enums.MarketType;

import java.time.LocalDateTime;
import java.util.List;

public interface FiveMinuteCandleService {
    FiveMinuteCandle findMinuteCandleByKstDate(MarketType market, LocalDateTime kst);

    List<FiveMinuteCandle> findMinutesCandleByTimestamp(MarketType market, long timestamp, int maxCount);

    FiveMinuteCandle findNextMinuteCandle(MarketType market, long timestamp);
}
