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

    public void ask(Double tradePrice, Double commission) {
        balance = tradePrice * volume - commission;
        volume = null;
        profits = null;
        profitsRate = null;
        maxProfitRate = null;
        avgBuyPrice = null;
        totalPrice = null;
        valuationAmount = null;
    }

    public void bid(Double tradePrice, Double volume, Double totalBidPrice, Double commission) {
        balance = 0D;
        this.volume = volume;
        avgBuyPrice = tradePrice;
        totalPrice = totalBidPrice - commission;
        valuationAmount = totalBidPrice - commission;

        profits = valuationAmount - totalBidPrice;
        profitsRate = profits / totalBidPrice * 100;
        maxProfitRate = Objects.isNull(maxProfitRate) ? profitsRate : Math.max(maxProfitRate, profitsRate);
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
