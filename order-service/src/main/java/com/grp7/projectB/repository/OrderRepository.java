package com.grp7.projectB.repository;

import com.grp7.projectB.model.aggregates.OrderAggregate;
import com.grp7.projectB.model.aggregates.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {

    Optional<OrderAggregate> findByOrderId(OrderId orderId);

    Optional<OrderAggregate> deleteByOrderId(OrderId orderId);
}
