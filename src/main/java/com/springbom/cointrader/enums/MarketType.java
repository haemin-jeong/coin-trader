package com.springbom.cointrader.enums;

import java.util.Arrays;

public enum MarketType {
    KRW_BTC("KRW-BTC");

    private final String urlValue;

    MarketType(String urlValue) {
        this.urlValue = urlValue;
    }

    public String getUrlValue() {
        return urlValue;
    }

    public static MarketType from(String urlValue) throws IllegalArgumentException {
        return Arrays.stream(MarketType.values())
                .filter(m -> m.getUrlValue().equals(urlValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 urlValue입니다."));
    }
}
