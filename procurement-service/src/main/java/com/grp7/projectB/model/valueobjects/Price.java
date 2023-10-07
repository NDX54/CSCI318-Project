package com.grp7.projectB.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Price {

    private Double price;

    public Price() {}

    public Price(Double price) { this.price = price; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() { return price.toString(); }
}
