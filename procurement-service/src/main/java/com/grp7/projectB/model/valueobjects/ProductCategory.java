package com.grp7.projectB.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductCategory {

    private String productCategory;

    public ProductCategory() {}

    public ProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getProductCategory() { return productCategory; }

    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    @Override
    public String toString() { return productCategory; }
}
