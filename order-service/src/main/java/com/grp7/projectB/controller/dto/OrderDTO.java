package com.grp7.projectB.controller.dto;

import com.grp7.projectB.model.aggregates.ProductId;

public class OrderDTO {

    private ProductId productId;

    private int quantity;

    public OrderDTO() {}

    public OrderDTO(ProductId productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
