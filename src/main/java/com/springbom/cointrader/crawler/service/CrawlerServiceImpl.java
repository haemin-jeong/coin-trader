package com.springbom.cointrader.crawler.service;

import com.springbom.cointrader.client.UpbitCandleClient;
import com.springbom.cointrader.crawler.entity.FiveMinuteCandle;
import com.springbom.cointrader.crawler.repository.FiveMinuteCandleRepository;
import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.util.MinuteCandleConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrawlerServiceImpl implements CrawlerService {

    private final UpbitCandleClient upbitCandleClient;
    private final FiveMinuteCandleRepository fiveMinuteCandleRepository;

    @Override
    @Transactional
    public void saveFiveMinuteCandlesByPeriod(MarketType marketType, LocalDateTime from, LocalDateTime to) {
        List<FiveMinuteCandle> minuteCandles = upbitCandleClient.getFiveMinuteCandlesByPeriod(marketType, from, to).stream()
                .map(MinuteCandleConverter::convertFiveMinuteCandle)
                .collect(Collectors.toList());
        fiveMinuteCandleRepository.saveAll(minuteCandles);
    }
}
