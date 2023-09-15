package com.grp7.repository;

import com.grp7.model.events.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
