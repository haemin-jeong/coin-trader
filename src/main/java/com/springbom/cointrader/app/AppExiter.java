package com.springbom.cointrader.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppExiter {

    private final ApplicationContext applicationContext;

    public AppExiter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void exit() {
        SpringApplication.exit(applicationContext, () -> 42);
    }
}
