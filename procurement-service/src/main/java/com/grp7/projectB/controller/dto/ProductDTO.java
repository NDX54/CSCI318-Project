package com.grp7.projectB.controller.dto;

import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.valueobjects.Name;
import com.grp7.projectB.model.valueobjects.Price;
import com.grp7.projectB.model.valueobjects.ProductCategory;

public class ProductDTO {

    private ProductId productId;

    private ProductCategory productCategory;

    private Name name;

    private Price price;

    public ProductDTO() {}

    public ProductId getProductId() { return productId; }

    public void setProductId(ProductId productId) { this.productId = productId; }

    public ProductCategory getProductCategory() { return productCategory; }

    public void setProductCategory(ProductCategory productCategory) { this.productCategory = productCategory; }

    public Name getName() { return name; }

    public void setName(Name name) { this.name = name; }

    public Price getPrice() { return price; }

    public void setPrice(Price price) { this.price = price; }
}
