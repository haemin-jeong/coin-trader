package com.springbom.cointrader.backtester.strategy;

import com.springbom.cointrader.util.rsi.RSIs;
import org.springframework.stereotype.Component;

@Component
public class RsiStrategy implements Strategy {

    @Override
    public boolean isAskable(RSIs rsi) {
        return rsi.isOver(70D);
    }

    @Override
    public boolean isBidable(RSIs rsi) {
        return rsi.isUnder(30D);
    }
}
