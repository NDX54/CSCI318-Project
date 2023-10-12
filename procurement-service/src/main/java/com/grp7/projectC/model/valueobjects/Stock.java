package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class Stock {

    @Min(value = 0, message = "Product's stock must be equal or greater than 0")
    private Integer stock;

    public Stock() {}

    public Stock(int stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return stock.toString();
    }
}
