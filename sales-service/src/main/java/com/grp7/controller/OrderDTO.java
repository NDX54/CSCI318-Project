package com.grp7.controller;

import com.grp7.model.valueobjects.Quantity;

import java.util.UUID;

public class OrderDTO {

    private String orderId;
    private String supplier;
    private String product;
    private Quantity quantity;

    // No-args constructor for object deserialization
    public OrderDTO() {
    }

    public OrderDTO(String orderId, String supplier, String product, Quantity quantity) {
        this.orderId = orderId;
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
