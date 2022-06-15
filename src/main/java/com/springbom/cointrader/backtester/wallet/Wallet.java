package com.springbom.cointrader.backtester.wallet;

import com.springbom.cointrader.enums.MarketType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MarketType market;

    @Column(name = "volume")
    private Double volume; // 수량

    @Column(name = "profits")
    private Double profits; // 수익금

    @Column(name = "profit_rate")
    private Double profitsRate; // 수익률

    @Column(name = "max_profit_rate")
    private Double maxProfitRate;

    @Column(name = "balance")
    private Double balance; // 잔액

    @Column(name = "avgBuyPrice")
    private Double avgBuyPrice; // 평단가

    @Column(name = "total_price")
    private Double totalPrice; // 총 매수액

    @Column(name = "valuation_amount")
    private Double valuationAmount; //평가 금액 = 보유수량 x 현재가


    public static Wallet of(MarketType market, Double balance) {
        return new Wallet(null, market, null, null, null, null, balance, null, null, null);
    }

    public void ask(Double openingPrice, double commission) {
        balance = openingPrice * volume - commission;
        volume = null;
        profits = null;
        profitsRate = null;
        maxProfitRate = null;
        avgBuyPrice = null;
        totalPrice = null;
        valuationAmount = null;
    }

    public void bid(Double openingPrice,  double volume, double totalPrice) {
        this.volume = volume;
        this.totalPrice = totalPrice;

        avgBuyPrice = openingPrice;
        valuationAmount = totalPrice;
        balance = 0.0D;
        profits = 0.0D;
        profitsRate = 0.0D;
        maxProfitRate  = Objects.isNull(maxProfitRate) ? profitsRate : maxProfitRate;
    }

    public void fetch(Double tradePrice) {
        if (Objects.isNull(volume) || volume == 0){
            return;
        }

        valuationAmount = tradePrice * getVolume();
        profits = valuationAmount - totalPrice;
        profitsRate = profits / getTotalPrice() * 100;
        maxProfitRate = Math.max(maxProfitRate, profitsRate);
    }

    public boolean isAskable() {
        return Objects.nonNull(volume) && volume > 0;
    }

    public boolean isBidable() {
        return Objects.isNull(volume) || volume == 0;
    }
}
