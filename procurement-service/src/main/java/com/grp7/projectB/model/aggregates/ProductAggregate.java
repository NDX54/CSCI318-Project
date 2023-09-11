package com.grp7.projectB.model.aggregates;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.grp7.projectB.model.valueobjects.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
public class ProductAggregate extends AbstractAggregateRoot<ProductAggregate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @JsonUnwrapped
    private ProductId productId;

    @Embedded
    @JsonUnwrapped
    private ProductCategory productCategory;

    @Embedded
    @JsonUnwrapped
    private Name name;

    @Embedded
    @JsonUnwrapped
    private Price price;

//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private ProductDetail productDetail;


    public ProductId getProductId() { return productId; }

    public void setProductId(ProductId productId) { this.productId = productId; }

    public ProductCategory getProductCategory() { return productCategory; }

    public void setProductCategory(ProductCategory productCategory) { this.productCategory = productCategory; }

    public Name getName() { return name; }

    public void setName(Name name) { this.name = name; }

    public Price getPrice() { return price; }

    public void setPrice(Price price) { this.price = price; }

//    public ProductDetail getProductDetail() { return productDetail; }
//
//    public void setProductDetail(ProductDetail productDetail) { this.productDetail = productDetail; }
}
