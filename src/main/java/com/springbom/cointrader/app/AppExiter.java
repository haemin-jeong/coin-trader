package com.springbom.cointrader.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppExiter {

    private final ApplicationContext applicationContext;

    public void exit() {
        SpringApplication.exit(applicationContext, () -> 42);
    }
}
