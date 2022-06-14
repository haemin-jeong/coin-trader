package com.springbom.cointrader.backtester.wallet;

import com.springbom.cointrader.config.UpbitConst;
import com.springbom.cointrader.enums.MarketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    public void askWallet(Wallet wallet, Double openingPrice) {
        wallet.ask(openingPrice, UpbitConst.commissionRate);
        walletRepository.save(wallet);
    }

    @Transactional
    public void bidWallet(Wallet wallet, Double openingPrice) {
        double totalPrice = wallet.getBalance() * UpbitConst.commissionRate;
        double volume = wallet.getBalance() / openingPrice;
        wallet.bid(openingPrice, volume,  totalPrice, UpbitConst.commissionRate);
        walletRepository.save(wallet);
    }

    @Transactional
    public void clear() {
        walletRepository.deleteAll();
    }

    @Transactional
    public Wallet createWallet(MarketType marketType, double initBalance) {
        return walletRepository.save(Wallet.of(marketType, initBalance));
    }

    @Transactional
    public void updateWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
