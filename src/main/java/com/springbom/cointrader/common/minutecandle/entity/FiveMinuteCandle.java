package com.springbom.cointrader.common.minutecandle.entity;

import com.springbom.cointrader.enums.MarketType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FiveMinuteCandle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "minute_candle_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "market_type")
    private MarketType marketType;

    @Column(name = "candle_date_time_utc")
    private LocalDateTime candleDateTimeUtc;

    @Column(name = "candle_date_time_kst")
    private LocalDateTime candleDateTimeKst;

    @Column(name = "opening_price")
    private Double openingPrice;

    @Column(name = "high_price")
    private Double highPrice;

    @Column(name = "low_price")
    private Double lowPrice;

    @Column(name = "trade_price")
    private Double tradePrice;

    @Column(name = "timestamps")
    private Long timestamp;

    @Column(name = "cnadle_acc_trade_price")
    private Double candleAccTradePrice;

    @Column(name = "candle_acc_trade_volume")
    private Double candleAccTradeVolume;

    public static FiveMinuteCandle of(MarketType marketType, LocalDateTime candleDateTimeUtc, LocalDateTime candleDateTimeKst, Double openingPrice, Double highPrice, Double lowPrice, Double tradePrice, Long timestamp, Double candleAccTradePrice, Double candleAccTradeVolume) {
        return new FiveMinuteCandle(null, marketType, candleDateTimeUtc, candleDateTimeKst, openingPrice, highPrice, lowPrice, tradePrice, timestamp, candleAccTradePrice, candleAccTradeVolume);
    }
}
