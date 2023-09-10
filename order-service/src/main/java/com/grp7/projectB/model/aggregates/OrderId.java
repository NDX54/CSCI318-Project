package com.grp7.projectB.model.aggregates;

import javax.persistence.Column;
import java.io.Serializable;

public class OrderId implements Serializable {

    @Column(name = "order_id")
    private String orderId;

    public OrderId() {}

    public OrderId(String orderId) { this.orderId = orderId; }

    public String getOrderId() { return this.orderId; }
}
