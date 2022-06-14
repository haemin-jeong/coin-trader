package com.springbom.cointrader.backtester.strategy;

import com.springbom.cointrader.util.rsi.RSIs;

public interface Strategy {

    boolean isAskable(RSIs rsi);

    boolean isBidable(RSIs rsI);
}
