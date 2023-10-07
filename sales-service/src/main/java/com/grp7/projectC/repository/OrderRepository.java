package com.grp7.projectC.repository;

import com.grp7.projectC.model.aggregates.OrderAggregate;
import com.grp7.projectC.model.aggregates.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {

    Optional<OrderAggregate> findByOrderId(OrderId orderId);

    Optional<OrderAggregate> deleteByOrderId(OrderId orderId);

}
