package com.grp7.projectB.repository;

import com.grp7.projectB.domain.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}
