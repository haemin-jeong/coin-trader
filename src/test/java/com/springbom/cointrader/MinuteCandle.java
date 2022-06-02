package com.springbom.cointrader;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MinuteCandle {

    @JsonProperty("market")
    private String market;

    @JsonProperty("candle_date_time_utc")
    private LocalDateTime candleDateTimeUtc;

    @JsonProperty("candle_date_time_kst")
    private LocalDateTime candleDateTimeKst;

    @JsonProperty("opening_price")
    private Double openingPrice;

    @JsonProperty("high_price")
    private Double highPrice;

    @JsonProperty("low_price")
    private Double lowPrice;

    @JsonProperty("trade_price")
    private Double tradePrice;

    private Long timestamp;

    @JsonProperty("candle_acc_trade_price")
    private Double candleAccTradePrice;

    @JsonProperty("candle_acc_trade_volume")
    private Double candleAccTradeVolume;



    public String getMarket() {
        return market;
    }

    public LocalDateTime getCandleDateTimeUtc() {
        return candleDateTimeUtc;
    }

    public LocalDateTime getCandleDateTimeKst() {
        return candleDateTimeKst;
    }

    public Double getOpeningPrice() {
        return openingPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getCandleAccTradePrice() {
        return candleAccTradePrice;
    }

    public Double getCandleAccTradeVolume() {
        return candleAccTradeVolume;
    }
}
