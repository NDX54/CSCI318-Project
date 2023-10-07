package com.grp7.projectB.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class OrdersMade {

    private Integer ordersMade;

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
