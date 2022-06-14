package com.springbom.cointrader.enums;

import java.text.MessageFormat;
import java.util.Arrays;

public enum SideType {

    ASK("ask"), //매도
    BID("bid"); //매수

    private String urlValue;

    SideType(String urlValue) {
        this.urlValue = urlValue;
    }

    public static SideType find(String urlValue) {
        return Arrays.stream(SideType.values())
                .filter(type -> type.getUrlValue().equals(urlValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("존재하지 않는 urlValue입니다. 입력된 값={0}", urlValue)));
    }

    public String getUrlValue() {
        return urlValue;
    }
}
