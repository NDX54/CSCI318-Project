package com.grp7.projectC.repository;

import com.grp7.projectC.model.events.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
