package com.springbom.cointrader;

public enum MinuteType {
    FIVE(5);

    private int minute;

    MinuteType(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }
}
