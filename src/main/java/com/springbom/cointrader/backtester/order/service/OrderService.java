package com.springbom.cointrader.backtester.order.service;

import com.springbom.cointrader.backtester.wallet.Wallet;

import java.time.LocalDateTime;

public interface OrderService {

    void ask(Wallet wallet, Double tradePrice, LocalDateTime candleDatetime);

    void bid(Wallet wallet, Double tradePrice, LocalDateTime candleDateTime);

    void clear();
}
