package com.springbom.cointrader.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "slack")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class SlackKeyProperties {

    private final String key;
    private final String channel;
}
