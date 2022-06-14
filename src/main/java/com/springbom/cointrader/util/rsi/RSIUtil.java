package com.springbom.cointrader.util.rsi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RSIUtil {

    public static RSIs getRSI14(List<Double> prices) {
        RSI rsi = new RSI(14);
        double[] rawDoubles = prices.stream().mapToDouble(p -> p).toArray();

        return RSIs.of(Arrays.stream(rsi.count(rawDoubles))
                .boxed()
                .collect(Collectors.toList()));
    }
}
