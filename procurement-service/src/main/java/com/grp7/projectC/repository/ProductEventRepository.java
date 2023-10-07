package com.grp7.projectC.repository;

import com.grp7.projectC.model.events.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {


}
