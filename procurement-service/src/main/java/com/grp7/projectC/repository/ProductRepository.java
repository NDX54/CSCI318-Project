package com.grp7.projectC.repository;

import com.grp7.projectC.model.aggregates.ProductAggregate;
import com.grp7.projectC.model.aggregates.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductAggregate, String> {
//    @Query("SELECT p FROM ProductAggregate p JOIN FETCH p.productDetail")
//    List<ProductAggregate> findAllWithProductDetail();

    Optional<ProductAggregate> findByProductId(ProductId productId);

//    Optional<ProductAggregate> findByProductIdToString(String productId);

    Optional<ProductAggregate> findByName(String name);

    Optional<ProductAggregate> deleteByProductId(ProductId productId);
}
