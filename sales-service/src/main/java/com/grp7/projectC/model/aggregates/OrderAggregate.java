package com.grp7.projectC.model.aggregates;

import com.grp7.projectC.model.valueobjects.Quantity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class OrderAggregate {

    @EmbeddedId
    @Column
    private OrderId orderId; // Unique identifier for the OrderAggregate

    @Column
    @NotNull
    private String customerId;

    @Column
    @NotNull
    private String productId;

    @Column
    @NotBlank(message = "Supplier must not be left blank")
    private String supplier;

    @Column
    private String product;

    @Column
    private Quantity quantity;


    protected OrderAggregate() {
    }

    public OrderAggregate(OrderId orderId, String customerId, String productId, String supplier, String product, Quantity quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return "OrderAggregate{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", supplier=" + supplier + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

}
