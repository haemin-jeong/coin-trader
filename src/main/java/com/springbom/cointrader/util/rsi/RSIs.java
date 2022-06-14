package com.springbom.cointrader.util.rsi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RSIs {

    private List<Double> rsi;

    public boolean isUnder(double value) {
        return rsi.get(0) < value;
    }

    public boolean isOver(double value) {
        return rsi.get(0) > value;
    }

    public boolean isUndering(int value) {
        if(rsi.get(1) > value && rsi.get(0) < value) {
            return true;
        }
        return false;
    }

    public boolean isOvering(int value) {
        if(rsi.get(1) < value && rsi.get(0) > value) {
            return true;
        }
        return false;
    }

    public static RSIs of(List<Double> rsi) {
        return new RSIs(rsi);
    }
}