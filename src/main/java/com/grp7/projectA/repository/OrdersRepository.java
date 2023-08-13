package com.grp7.projectA.repository;

import com.grp7.projectA.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
