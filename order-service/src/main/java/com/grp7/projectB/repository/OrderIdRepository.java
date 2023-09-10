package com.grp7.projectB.repository;

import com.grp7.projectB.domain.model.aggregates.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderIdRepository extends JpaRepository<OrderId, String> {
}
