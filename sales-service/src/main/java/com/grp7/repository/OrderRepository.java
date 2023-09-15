package com.grp7.repository;

import com.grp7.model.aggregates.OrderAggregate;
import com.grp7.model.aggregates.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {

    // Custom queries (if needed) can be added here

    // Example: Find OrderAggregate by its UUID
//    OrderAggregate findByOrderId(UUID orderId);

    Optional<OrderAggregate> findByOrderId(OrderId orderId);

    Optional<OrderAggregate> deleteByOrderId(OrderId orderId);

    boolean existsById(UUID orderId);
}