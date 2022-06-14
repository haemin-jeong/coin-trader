package com.springbom.cointrader.common.minutecandle.service;

import com.springbom.cointrader.common.minutecandle.entity.FiveMinuteCandle;
import com.springbom.cointrader.common.minutecandle.repository.FiveMinuteCandleRepository;
import com.springbom.cointrader.enums.MarketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FiveMinuteCandleServiceImpl implements FiveMinuteCandleService {

    private final FiveMinuteCandleRepository candleRepository;

    public FiveMinuteCandle findMinuteCandleByKstDate(MarketType market, LocalDateTime kst) {
        return candleRepository.findFiveMinuteCandleByKstDate(market, kst);
    }

    public List<FiveMinuteCandle> findMinutesCandleByTimestamp(MarketType market, long timestamp, int maxCount) {
        return candleRepository.findFiveMinuteCandleByTimestamp(market, timestamp, maxCount);
    }

    public FiveMinuteCandle findNextMinuteCandle(MarketType market, long timestamp) {
        return candleRepository.findNextFiveMinuteCandle(market, timestamp);
    }
}
