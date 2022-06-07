package com.springbom.cointrader.config;

import com.springbom.cointrader.enums.ExecuteType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "application")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class ExecuteMode {

    private final ExecuteType mode;

}
