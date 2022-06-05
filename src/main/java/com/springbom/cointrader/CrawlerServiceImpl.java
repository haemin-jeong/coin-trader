package com.springbom.cointrader;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CrawlerServiceImpl implements CrawlerService {

    private final UpbitCandleClient upbitCandleClient;
    private final MinuteCandleRepository minuteCandleRepository;

    public CrawlerServiceImpl(UpbitCandleClient upbitCandleClient, MinuteCandleRepository minuteCandleRepository) {
        this.upbitCandleClient = upbitCandleClient;
        this.minuteCandleRepository = minuteCandleRepository;
    }

    @Override
    @Transactional
    public void saveMinuteCandlesByPeriod(MinuteType minuteType, MarketType marketType, LocalDateTime from, LocalDateTime to) {
        List<MinuteCandle> minuteCandles = upbitCandleClient.getMinuteCandlesByPeriod(minuteType, marketType, from, to).stream()
                .map(MinuteCandleConverter::toEntity)
                .collect(Collectors.toList());
        minuteCandleRepository.saveAll(minuteCandles);
    }
}
