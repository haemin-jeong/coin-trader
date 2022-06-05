package com.springbom.cointrader.util;

import com.springbom.cointrader.crawler.dto.MinuteCandleResponse;
import com.springbom.cointrader.crawler.entity.MinuteCandle;
import com.springbom.cointrader.enums.MarketType;

public class MinuteCandleConverter {

    public static MinuteCandle toEntity(MinuteCandleResponse response) {
        return MinuteCandle.of(
                MarketType.from(response.getMarket()),
                response.getCandleDateTimeUtc(),
                response.getCandleDateTimeKst(),
                response.getOpeningPrice(),
                response.getHighPrice(),
                response.getLowPrice(),
                response.getTradePrice(),
                response.getTimestamp(),
                response.getCandleAccTradePrice(),
                response.getCandleAccTradeVolume()
        );
    }
}
