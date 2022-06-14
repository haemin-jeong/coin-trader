package com.springbom.cointrader.backtester.order.entity;

import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.enums.SideType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "side")
    private SideType side; // 거래 유형

    @Enumerated(EnumType.STRING)
    @Column(name = "market")
    private MarketType market;

    @Column(name = "volume")
    private Double volume; // 거래 개수

    @Column(name = "price")
    private Double price; // 코인당 가격

    @Column(name = "commission")
    private Double commission;

    @Column(name = "profit")
    private Double profit; // 수익금

    @Column(name = "profit_rate")
    private Double profitRate; // 수익률

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "candle_date_time")
    LocalDateTime candleDateTime;

    public static Order of(SideType side, MarketType market, Double volume, Double price, Double commission, Double profits, Double profitsRate, LocalDateTime candleDateTime) {
        return new Order(null, side, market, volume, price, commission, profits, profitsRate, LocalDateTime.now(), candleDateTime);
    }

    public static Order createAskingOrder(MarketType market, Double volume, Double price, Double commission, Double profits, Double profitsRate, LocalDateTime candleDateTime) {
        return of(SideType.ASK, market, volume, price, commission, profits, profitsRate, candleDateTime);
    }

    public static Order createBiddingOrder(MarketType market, Double volume, Double price, Double commission, LocalDateTime candleDateTime) {
        return of(SideType.BID, market, volume, price, commission, null, null, candleDateTime);
    }
}
