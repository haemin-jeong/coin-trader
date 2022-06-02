package com.springbom.cointrader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UrlBuilder {

    public static String buildMinuteCandleUrl(int minutes, MarketType marketType, int count, LocalDateTime to) {
        return new StringBuilder("https://api.upbit.com/v1/candles/minutes/")
                .append(minutes)
                .append("?market=").append(marketType.getUrlValue())
                .append("&count=").append(count)
                .append("&to=").append(to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .toString();
    }
}
