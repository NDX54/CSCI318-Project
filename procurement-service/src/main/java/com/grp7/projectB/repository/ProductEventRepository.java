package com.grp7.projectB.repository;

import com.grp7.projectB.model.events.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {


}
