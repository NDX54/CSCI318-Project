package com.grp7.projectB.repository;

import com.grp7.projectB.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.productDetail")
    List<Product> findAllWithProductDetail();
}