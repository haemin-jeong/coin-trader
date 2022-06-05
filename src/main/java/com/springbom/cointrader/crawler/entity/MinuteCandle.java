package com.springbom.cointrader.crawler.entity;

import com.springbom.cointrader.enums.MarketType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MinuteCandle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "minute_candle_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MarketType marketType;

    private LocalDateTime candleDateTimeUtc;

    private LocalDateTime candleDateTimeKst;

    private Double openingPrice;

    private Double highPrice;

    private Double lowPrice;

    private Double tradePrice;

    private Long timestamp;

    private Double candleAccTradePrice;

    private Double candleAccTradeVolume;

    protected MinuteCandle() {
    }

    public MinuteCandle(Long id, MarketType marketType, LocalDateTime candleDateTimeUtc, LocalDateTime candleDateTimeKst, Double openingPrice, Double highPrice, Double lowPrice, Double tradePrice, Long timestamp, Double candleAccTradePrice, Double candleAccTradeVolume) {
        this.id = id;
        this.marketType = marketType;
        this.candleDateTimeUtc = candleDateTimeUtc;
        this.candleDateTimeKst = candleDateTimeKst;
        this.openingPrice = openingPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.tradePrice = tradePrice;
        this.timestamp = timestamp;
        this.candleAccTradePrice = candleAccTradePrice;
        this.candleAccTradeVolume = candleAccTradeVolume;
    }

    public static MinuteCandle of(MarketType marketType, LocalDateTime candleDateTimeUtc, LocalDateTime candleDateTimeKst, Double openingPrice, Double highPrice, Double lowPrice, Double tradePrice, Long timestamp, Double candleAccTradePrice, Double candleAccTradeVolume) {
        return new MinuteCandle(null, marketType, candleDateTimeUtc, candleDateTimeKst, openingPrice, highPrice, lowPrice, tradePrice, timestamp, candleAccTradePrice, candleAccTradeVolume);
    }
}
