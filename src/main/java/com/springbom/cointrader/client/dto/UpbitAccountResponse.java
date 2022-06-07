package com.springbom.cointrader.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpbitAccountResponse {

    private String currency; // 화폐를 의미하는 영문 대문자 코드

    private String balance; //주문가능 금액/수량

    private String locked; //주문 중 묶여있는 금액/수량

    @JsonProperty("avg_buy_price")
    private String avgBuyPrice; //매수평균가

    @JsonProperty("avg_buy_price_modified")
    private Boolean avgBuyPriceModified; // 매수평균가 수정 여부

    @JsonProperty("unit_currency")
    private String unitCurrency; // 평단가 기준 화폐
}