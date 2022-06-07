package com.springbom.cointrader.config;

import com.springbom.cointrader.enums.ExecuteType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "application")
@ConstructorBinding
public class ExecuteMode {

    private final ExecuteType mode;

    public ExecuteMode(ExecuteType mode) {
        this.mode = mode;
    }

    public ExecuteType getMode() {
        return mode;
    }
}
