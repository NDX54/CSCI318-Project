package com.grp7.projectA.repository;

import com.grp7.projectA.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long> {
}
