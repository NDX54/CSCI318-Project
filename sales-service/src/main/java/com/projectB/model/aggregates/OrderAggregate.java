package com.projectB.model.aggregates;

import com.grp7.projectB.model.aggregates.CustomerId;
import com.projectB.model.valueobjects.Quantity;

import javax.persistence.*;

@Entity
public class OrderAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private OrderId orderId; // Unique identifier for the OrderAggregate
    @Column
    private String customerId;

    @Column
    private String productId;

    @Column
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
