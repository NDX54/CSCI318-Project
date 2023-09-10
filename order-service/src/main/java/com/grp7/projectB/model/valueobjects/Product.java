package com.grp7.projectB.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Product {

    @Column(name = "product_name", unique = false, updatable = true)
    private String product;

    public Product() {}

    public Product(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
