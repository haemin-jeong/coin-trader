package com.springbom.cointrader.util;

import com.springbom.cointrader.crawler.dto.FiveMinuteCandleResponse;
import com.springbom.cointrader.crawler.entity.FiveMinuteCandle;
import com.springbom.cointrader.enums.MarketType;

public class MinuteCandleConverter {

    public static FiveMinuteCandle convertFiveMinuteCandle(FiveMinuteCandleResponse response) {
        return FiveMinuteCandle.of(
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
