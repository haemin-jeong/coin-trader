package com.springbom.cointrader;

public enum MinuteType {
    ONE(1), THREE(3), FIVE(5), FIFTEEN(15), THIRTEEN(30), HOUR(60);

    private int minute;

    MinuteType(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }
}
