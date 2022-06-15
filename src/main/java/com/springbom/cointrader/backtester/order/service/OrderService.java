package com.springbom.cointrader.backtester.order.service;

import com.springbom.cointrader.backtester.order.entity.Order;
import com.springbom.cointrader.backtester.wallet.Wallet;

import java.time.LocalDateTime;

public interface OrderService {

    Order ask(Wallet wallet, Double tradePrice, LocalDateTime candleDatetime);

    Order bid(Wallet wallet, Double tradePrice, LocalDateTime candleDateTime);

    void clear();
}
