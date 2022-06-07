package com.springbom.cointrader.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MarketType {
    KRW_BTC("KRW-BTC");

    private final String urlValue;

    public static MarketType from(String urlValue) throws IllegalArgumentException {
        return Arrays.stream(MarketType.values())
                .filter(m -> m.getUrlValue().equals(urlValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 urlValue입니다."));
    }
}
