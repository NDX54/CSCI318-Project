package com.grp7.projectC.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class ProductId implements Serializable {
    private String productId;

    public ProductId() {}

    public ProductId(String productId) { this.productId = productId; }

    public String getProductId() { return this.productId; }

    public void setProductId(String productId) { this.productId = productId; }

    @Override
    public String toString() { return productId; }
}
