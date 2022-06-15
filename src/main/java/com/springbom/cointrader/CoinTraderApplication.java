package com.springbom.cointrader;

import com.springbom.cointrader.config.ExecuteMode;
import com.springbom.cointrader.config.SlackKeyProperties;
import com.springbom.cointrader.config.UpbitKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({UpbitKeyProperties.class, SlackKeyProperties.class, ExecuteMode.class})
public class CoinTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinTraderApplication.class, args);
    }
}
