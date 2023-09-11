package com.grp7.projectB.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {

    @Column(name = "price", unique = false, updatable = false)
    private Double price;

    public Price() {}

    public Price(Double price) { this.price = price; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}
