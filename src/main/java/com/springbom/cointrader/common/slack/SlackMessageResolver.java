package com.springbom.cointrader.common.slack;

import com.springbom.cointrader.enums.MarketType;
import java.time.LocalDateTime;
import java.util.Objects;

public interface SlackMessageResolver {
    static String resolveBidOrder(MarketType market, Double volume, Double price, Double commission, LocalDateTime candleDateTime) {
        return "[매수 주문 발생]" + "\n" +
                "코인 종류:" + market + "\n" +
                "구매 수량: " + volume + "\n" +
                "1코인당 가격: " + price + "\n" +
                "구매 수수료: " + commission + "\n" +
                "캔들 시간: " + candleDateTime;
    }

    static String resolveAskOrder(MarketType market, Double price, Double profit, Double profitRate, Double commission, LocalDateTime candleDateTime) {
        return "[매도 주문 발생]" + "\n" +
                "코인 종류:" + market + "\n" +
                "1코인당 가격: " + price + "\n" +
                "수익금: " + profit + "\n" +
                "수익률: " + profitRate + "\n" +
                "수수료: " + commission + "\n" +
                "캔들 시간: " + candleDateTime;
    }

    static String resolveStartBackTest(LocalDateTime start, LocalDateTime end) {
        return "[백테스트 시작 알림]" + "\n" +
                "기간: " + start + " ~ " + end;
    }

    static String resolveEndBackTest(Double balance, Double profitRate, Double maxProfitRate) {
        return "[백테스트 종료 알림]" + "\n" +
                "잔액: " + balance + "\n" +
                "최종 수익률:" + (Objects.isNull(profitRate) ? "X" : profitRate) + "\n" +
                "최대 수익률: " + (Objects.isNull(maxProfitRate) ? "X" : maxProfitRate);
    }
}
