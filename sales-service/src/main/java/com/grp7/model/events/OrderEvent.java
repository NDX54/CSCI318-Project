package com.grp7.model.events;

import com.grp7.model.aggregates.OrderAggregate;
import com.grp7.model.aggregates.OrderId;
import com.grp7.model.valueobjects.Quantity;

import javax.persistence.*;

@Entity
public class OrderEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String eventName;

    @Column
    private OrderId orderId;

    @Column
    private String supplier;

    @Column
    private String product;

    @Column
    private Quantity quantity;

    // No-args constructor for JPA
    protected OrderEvent() {
    }

    public OrderEvent(OrderId orderId, String supplier, String product, Quantity quantity) {
        this.orderId = orderId;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderEvent(OrderId orderId, String supplier, String product, Quantity quantity, OrderAggregate orderAggregate) {
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

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
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
