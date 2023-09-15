package com.grp7.projectB.controller.dto;

import com.grp7.projectB.model.aggregates.OrderId;
import com.grp7.projectB.model.aggregates.ProductId;

public class AddToOrderDTO {

    private ProductId productId;

    private OrderId orderId;

    public AddToOrderDTO() {}

    public AddToOrderDTO(ProductId productId, OrderId orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public ProductId getProductIdDTO() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }
}
