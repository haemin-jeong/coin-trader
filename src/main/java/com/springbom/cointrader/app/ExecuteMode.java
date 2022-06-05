package com.springbom.cointrader.app;

import com.springbom.cointrader.enums.ExecuteType;
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
