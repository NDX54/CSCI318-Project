package com.grp7.projectB.model.aggregates;

import javax.persistence.Column;

public class ProductId {

    @Column(name = "product_id")
    private String productId;

    public ProductId() {}

    public ProductId(String productId) { this.productId = productId; }

    public String getProductId() { return this.productId; }

    public void setProductId(String productId) { this.productId = productId; }
}
