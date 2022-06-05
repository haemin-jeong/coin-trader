package com.springbom.cointrader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExecuteMode {

    @Value("${application.mode}")
    private ExecuteType mode;

    public ExecuteType getMode() {
        return mode;
    }
}
