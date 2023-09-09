package com.grp7.projectB.repository;

import com.grp7.projectB.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdersRepository extends JpaRepository<Order, Long> {
}
