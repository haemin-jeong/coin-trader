package com.springbom.cointrader.crawler.repository;

import com.springbom.cointrader.crawler.entity.FiveMinuteCandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiveMinuteCandleRepository extends JpaRepository<FiveMinuteCandle, Long> {
}
