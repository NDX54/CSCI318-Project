package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class OrdersMade {

    // By default, all customers will have 0 orders made.
    private Integer ordersMade = 0;

    public OrdersMade() {}

    public OrdersMade(Integer ordersMade) {
        this.ordersMade = ordersMade;
    }

    public Integer getOrdersMade() { return ordersMade; }

    public Integer incrementOrdersMade() { return this.ordersMade++; }

    public Integer decrementOrdersMade() { return this.ordersMade--; }

    public void setOrdersMade(Integer ordersMade) {
        this.ordersMade = ordersMade;
    }
}
