package com.grp7.projectC.model.events;

import com.grp7.projectC.model.aggregates.OrderAggregate;
import com.grp7.projectC.model.aggregates.OrderId;
import com.grp7.projectC.model.valueobjects.Quantity;

import javax.persistence.*;

@Entity
public class OrderEvent {

    public static final String ORDER_CREATED = "ORDER_CREATED";


    public static final String ORDER_DELETED = "ORDER_DELETED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String eventName;

    @Column
    private String orderId;

    @Column
    private String customerId;

    @Column
    private String productId;

    @Column
    private String product;

    @Column
    private String supplier;

    @Column
    private Quantity quantity;

    // No-args constructor for JPA
    public OrderEvent() {
    }

    public OrderEvent(String orderId, String customerId, String productId, String supplier, String product, Quantity quantity) {
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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
