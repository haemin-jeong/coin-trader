package com.springbom.cointrader.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "upbit")
@ConstructorBinding
public class UpbitKeyProperties {
    
    private final String accessKey;
    private final String secretKey;

    public UpbitKeyProperties(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
