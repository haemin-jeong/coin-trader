package com.springbom.cointrader.backtester.order.service;

import static com.springbom.cointrader.config.UpbitConst.commissionRate;

import com.springbom.cointrader.backtester.order.entity.Order;
import com.springbom.cointrader.backtester.order.repository.OrderRepository;
import com.springbom.cointrader.backtester.wallet.Wallet;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void ask(Wallet wallet, Double tradePrice, LocalDateTime candleDatetime) {
        double valuationAmount = wallet.getVolume() * tradePrice; // 평가금액 = 보유수량 * 현재가
        double commission = valuationAmount * commissionRate;
        double profits = (valuationAmount - commission) - wallet.getTotalPrice(); // 평가손익 = 평가금액 - 매수금액
        double profitsRate = profits / wallet.getTotalPrice() * 100; // 수익률 = 평가손익 / 매수금액 * 100

        Order askingOrder = Order.createAskingOrder(wallet.getMarket(), wallet.getVolume(), tradePrice, commission, profits, profitsRate, candleDatetime);
        wallet.ask(tradePrice, commission);

        log.info("[매도 주문] {}, 평가금액={}, 수익금={}, 수익률={}, 캔들시간={}", wallet.getMarket(), valuationAmount, profits, profitsRate, candleDatetime);

        orderRepository.save(askingOrder);
    }

    @Transactional
    public void bid(Wallet wallet, Double tradePrice, LocalDateTime candleDateTime) {
        double commission = wallet.getBalance() * commissionRate;
        double allBidPrice = wallet.getBalance() - commission;
        double bidVolume = allBidPrice / tradePrice;

        Order biddingOrder = Order.createBiddingOrder(wallet.getMarket(), bidVolume, tradePrice, commission, candleDateTime);
        wallet.bid(tradePrice, bidVolume, allBidPrice);

        log.info("[매수 주문] {}, 구매 개수={}, 총 구매 비용={}, 캔들시간={}", wallet.getMarket(), bidVolume, allBidPrice, candleDateTime);

        orderRepository.save(biddingOrder);
    }

    @Transactional
    public void clear() {
        orderRepository.deleteAll();
    }
}
