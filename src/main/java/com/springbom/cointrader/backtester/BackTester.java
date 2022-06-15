package com.springbom.cointrader.backtester;

import com.springbom.cointrader.backtester.order.entity.Order;
import com.springbom.cointrader.backtester.order.service.OrderService;
import com.springbom.cointrader.backtester.strategy.Strategy;
import com.springbom.cointrader.backtester.wallet.Wallet;
import com.springbom.cointrader.backtester.wallet.WalletService;
import com.springbom.cointrader.common.minutecandle.entity.FiveMinuteCandle;
import com.springbom.cointrader.common.minutecandle.service.FiveMinuteCandleService;
import com.springbom.cointrader.enums.MarketType;
import com.springbom.cointrader.enums.MinuteType;
import com.springbom.cointrader.util.rsi.RSIUtil;
import com.springbom.cointrader.util.rsi.RSIs;
import com.springbom.cointrader.common.slack.SlackMessageSender;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BackTester {

    private final FiveMinuteCandleService minuteCandleService;
    private final WalletService walletService;
    private final OrderService orderService;

    private final SlackMessageSender sender;
    private final Strategy strategy;

    public void run(MarketType marketType, double initBalance, int limitCount, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime targetDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), 0, 0);
        boolean isRun = true;

        walletService.clear();
        orderService.clear();

        Wallet wallet = walletService.createWallet(marketType, initBalance);

        sender.sendStartBackTestMessage(startDate, endDate);

        while (isRun) {
            FiveMinuteCandle baseCandle = minuteCandleService.findMinuteCandleByKstDate(marketType, targetDate);

            if (Objects.isNull(baseCandle)) {
                targetDate = targetDate.plusMinutes(MinuteType.ONE.getMinute());
                continue;
            }

            //매수, 매도 기준이 되는 캔들
            FiveMinuteCandle targetCandle = minuteCandleService.findNextMinuteCandle(marketType, baseCandle.getTimestamp());

            //기준 시간으로부터 limitCount개의 캔들을 가져온다.
            List<FiveMinuteCandle> minuteCandles = minuteCandleService.findMinutesCandleByTimestamp(marketType, baseCandle.getTimestamp(), limitCount);

            List<Double> tradePrices = minuteCandles.stream().map(FiveMinuteCandle::getTradePrice).collect(Collectors.toUnmodifiableList());

            RSIs rsi = RSIUtil.getRSI14(tradePrices);

            runAskProcess(targetCandle, wallet, rsi);

            runBidProcess(targetCandle, wallet, rsi);

            wallet.fetch(targetCandle.getTradePrice());

            if (checkRunCondition(endDate, targetCandle)) {
                isRun = false;
            }

            targetDate = targetDate.plusMinutes(MinuteType.FIVE.getMinute());
        }

        sender.sendEndBackTestMessage(wallet.getBalance(), wallet.getProfitsRate(), wallet.getMaxProfitRate());

        walletService.updateWallet(wallet);
    }

    private boolean checkRunCondition(LocalDateTime endDate, FiveMinuteCandle targetCandle) {
        return endDate.isBefore(targetCandle.getCandleDateTimeKst());
    }

    private void runBidProcess(FiveMinuteCandle targetCandle, Wallet wallet, RSIs rsi) {
        if (wallet.isBidable() && strategy.isBidable(rsi)) {
            Order order = orderService.bid(wallet, targetCandle.getOpeningPrice(),
                    targetCandle.getCandleDateTimeKst());

            sender.sendBidMessage(order.getMarket(), order.getVolume(), order.getPrice(),
                    order.getCommission(), order.getCandleDateTime());
        }
    }

    private void runAskProcess(FiveMinuteCandle targetCandle, Wallet wallet, RSIs rsi) {
        if (wallet.isAskable() && strategy.isAskable(rsi)) {
            Order order = orderService.ask(wallet, targetCandle.getOpeningPrice(),
                    targetCandle.getCandleDateTimeKst());

            sender.sendAskMessage(order.getMarket(), order.getPrice(), order.getProfit(),
                    order.getProfitRate(), order.getCommission(), order.getCandleDateTime());
        }
    }
}
