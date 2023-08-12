package com.grp7.projectA.repository;

import com.grp7.projectA.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}
