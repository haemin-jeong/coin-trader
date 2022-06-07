package com.springbom.cointrader.crawler.service;

import com.springbom.cointrader.UpbitCandleClient;
import com.springbom.cointrader.util.MinuteCandleConverter;
import com.springbom.cointrader.crawler.entity.MinuteCandle;
import com.springbom.cointrader.crawler.repository.MinuteCandleRepository;
import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.enums.MinuteType;
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
    private final MinuteCandleRepository minuteCandleRepository;

    @Override
    @Transactional
    public void saveMinuteCandlesByPeriod(MinuteType minuteType, MarketType marketType, LocalDateTime from, LocalDateTime to) {
        List<MinuteCandle> minuteCandles = upbitCandleClient.getMinuteCandlesByPeriod(minuteType, marketType, from, to).stream()
                .map(MinuteCandleConverter::toEntity)
                .collect(Collectors.toList());
        minuteCandleRepository.saveAll(minuteCandles);
    }
}
