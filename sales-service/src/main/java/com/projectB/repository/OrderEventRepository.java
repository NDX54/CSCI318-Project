package com.projectB.repository;

import com.projectB.model.events.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
