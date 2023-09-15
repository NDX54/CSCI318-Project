package com.grp7.projectB.repository;

import com.grp7.projectB.model.events.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
