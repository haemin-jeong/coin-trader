package com.springbom.cointrader.crawler.repository;

import com.springbom.cointrader.crawler.entity.MinuteCandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinuteCandleRepository extends JpaRepository<MinuteCandle, Long> {
}
