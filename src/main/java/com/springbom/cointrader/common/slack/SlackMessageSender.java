package com.springbom.cointrader.common.slack;

import com.springbom.cointrader.client.SlackClient;
import com.springbom.cointrader.enums.MarketType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlackMessageSender {

    private final SlackClient client;

    public void sendStartBackTestMessage(LocalDateTime start, LocalDateTime end) {
        String startMessage = SlackMessageResolver.resolveStartBackTest(start, end);
        client.sendMessage(startMessage);
    }

    public void sendEndBackTestMessage(Double balance, Double profitRate, Double maxProfitRate) {
        String endMessage = SlackMessageResolver.resolveEndBackTest(balance, profitRate,
                maxProfitRate);
        client.sendMessage(endMessage);
    }

    public void sendBidMessage(MarketType market, Double volume, Double price, Double commission,
            LocalDateTime candleDateTime) {
        String slackMessage = SlackMessageResolver.resolveBidOrder(market, volume, price,
                commission, candleDateTime);
        client.sendMessage(slackMessage);
    }

    public void sendAskMessage(MarketType market, Double price, Double profit, Double profitRate,
            Double commission, LocalDateTime candleDateTime) {
        String slackMessage = SlackMessageResolver.resolveAskOrder(market, price, profit,
                profitRate, commission, candleDateTime);
        client.sendMessage(slackMessage);
    }
}
