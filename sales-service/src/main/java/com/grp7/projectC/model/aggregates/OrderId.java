package com.grp7.projectC.model.aggregates;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderId implements Serializable {

    private String orderId;

    public OrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderId() {

    }

//    public OrderId(UUID value) {
//        if (value == null) {
//            throw new IllegalArgumentException("OrderId value cannot be null.");
//        }
//        this.value = value;
//    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return this.orderId.equals(orderId.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return orderId;
    }
}
