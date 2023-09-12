package com.grp7.projectB.repository;

import com.grp7.projectB.model.events.ProductCreatedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductCreatedEvent, Long> {


}
