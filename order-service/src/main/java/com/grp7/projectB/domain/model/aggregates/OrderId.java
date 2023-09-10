package com.grp7.projectB.domain.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderId implements Serializable {

    @Column(name = "order_id")
    private long orderId;

    public OrderId() {}

    public OrderId(long orderId) { this.orderId = orderId; }

    public long getOrderId() { return this.orderId; }

}