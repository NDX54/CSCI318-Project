package com.grp7.projectB.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductCategory {

    @Column(name = "product_category", unique = false, updatable = false)
    private String productCategory;

    public ProductCategory() {}

    public ProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getProductCategory() { return productCategory; }

    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
}
