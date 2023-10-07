package com.grp7.projectB.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class ProductId implements Serializable {

    @Column(name = "product_id")
    private String productId;

    public ProductId() {}

    public ProductId(String productId) { this.productId = productId; }

    public String getProductId() { return this.productId; }

    public void setProductId(String productId) { this.productId = productId; }

    @Override
    public String toString() { return productId; }
}
