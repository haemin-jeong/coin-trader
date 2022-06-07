package com.springbom.cointrader.app;

import com.springbom.cointrader.backtester.BackTester;
import com.springbom.cointrader.config.ExecuteMode;
import com.springbom.cointrader.crawler.Crawler;
import com.springbom.cointrader.trader.Trader;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final ExecuteMode mode;
    private final AppExiter exiter;

    private final Crawler crawler;
    private final Trader trader;
    private final BackTester backTester;

    @Override
    public void run(ApplicationArguments args) {
        try {
            switch (mode.getMode()) {
                case CRAWL:
                    crawler.run();
                    break;
                case BACK_TEST:
                    backTester.run();
                    break;
                case REAL_TRADE:
                    trader.run();
                    break;
                default:
                    throw new RuntimeException();
            }
        } catch (Exception e) {
            log.error("잘못된 ExecuteMode 값 입니다. 현재 ExecuteMode={}", mode.getMode(), e);
        } finally {
            exiter.exit();
        }
    }
}
