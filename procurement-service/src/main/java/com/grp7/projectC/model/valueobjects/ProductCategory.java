package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class ProductCategory {

    @NotBlank(message = "Product category must not be blank")
    private String productCategory;

    public ProductCategory() {}

    public ProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getProductCategory() { return productCategory; }

    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    @Override
    public String toString() { return productCategory; }
}
