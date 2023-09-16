package com.projectB.controller;

import com.grp7.projectB.model.aggregates.CustomerId;
import com.projectB.model.valueobjects.Quantity;

import java.util.UUID;

public class OrderDTO {

    private String orderId;

    private CustomerId customerId;
    private String supplier;
    private String product;
    private Quantity quantity;

    // No-args constructor for object deserialization
    public OrderDTO() {
    }

    public OrderDTO(String orderId, CustomerId customerId, String supplier, String product, Quantity quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }

    // Standard getters and setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
