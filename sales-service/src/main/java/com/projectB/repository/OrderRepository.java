package com.projectB.repository;

import com.projectB.model.aggregates.OrderAggregate;
import com.projectB.model.aggregates.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {

    Optional<OrderAggregate> findByOrderId(OrderId orderId);

    Optional<OrderAggregate> deleteByOrderId(OrderId orderId);

}
