package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Min;
import java.util.Objects;

@Embeddable
public class Quantity {


    private Integer quantity;

    // No-args constructor for JPA
    public Quantity() {}

    public Quantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public Integer getQuantity() {
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
