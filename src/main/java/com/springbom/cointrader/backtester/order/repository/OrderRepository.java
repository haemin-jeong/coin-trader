package com.springbom.cointrader.backtester.order.repository;

import com.springbom.cointrader.backtester.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
