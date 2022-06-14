package com.springbom.cointrader.backtester.wallet;

import com.springbom.cointrader.enums.MarketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByMarket(MarketType market);
}
