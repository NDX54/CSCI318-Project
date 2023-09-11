package com.grp7.projectB.repository;

import com.grp7.projectB.model.aggregates.ProductAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductDetailRepository extends JpaRepository<ProductAggregate, String> {

}
