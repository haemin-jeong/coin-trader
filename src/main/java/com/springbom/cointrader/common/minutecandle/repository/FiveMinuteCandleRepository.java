package com.springbom.cointrader.common.minutecandle.repository;

import com.springbom.cointrader.common.minutecandle.entity.FiveMinuteCandle;
import com.springbom.cointrader.enums.MarketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FiveMinuteCandleRepository extends JpaRepository<FiveMinuteCandle, Long> {
    @Query(value = "select m from FiveMinuteCandle m where m.marketType = :market and m.candleDateTimeKst = :kst")
    FiveMinuteCandle findFiveMinuteCandleByKstDate(@Param("market") MarketType marketType, @Param("kst") LocalDateTime kstDate);

    @Query(nativeQuery = true, value = "SELECT * FROM five_minute_candle WHERE market_type = :#{#market?.name()} AND timestamps <= :timestamps ORDER BY timestamps DESC LIMIT :limit")
    List<FiveMinuteCandle> findFiveMinuteCandleByTimestamp(@Param("market") MarketType market, @Param("timestamps") long timestamps, @Param("limit") int limitCount);

    @Query(nativeQuery = true, value = "SELECT * FROM five_minute_candle WHERE market_type = :#{#market?.name()} AND timestamps > :timestamps ORDER BY timestamps ASC LIMIT 1")
    FiveMinuteCandle findNextFiveMinuteCandle(@Param("market") MarketType market, @Param("timestamps") long timestamps);
}
