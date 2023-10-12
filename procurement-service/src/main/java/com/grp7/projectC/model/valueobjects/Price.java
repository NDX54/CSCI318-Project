package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Embeddable
public class Price {

    @DecimalMin(value = "0.0", message = "Price must be equal or greater than 0")
    private Double price;

    public Price() {}

    public Price(Double price) { this.price = price; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() { return price.toString(); }
}
