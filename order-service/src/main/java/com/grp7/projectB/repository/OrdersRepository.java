package com.grp7.projectB.repository;

import com.grp7.projectB.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
