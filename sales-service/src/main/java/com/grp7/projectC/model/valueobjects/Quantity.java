package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Quantity {

    private int quantity;

    // No-args constructor for JPA
    protected Quantity() {
    }

    public Quantity(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return this.quantity == quantity.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }

}
