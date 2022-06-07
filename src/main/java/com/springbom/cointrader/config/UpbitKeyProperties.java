package com.springbom.cointrader.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "upbit")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class UpbitKeyProperties {
    
    private final String accessKey;
    private final String secretKey;

}
