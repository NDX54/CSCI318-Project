package com.grp7.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productCategory;

    @Column
    private String name;

    @Column
    private Double price;

    // Default constructor for JPA
    protected Product() {
    }

    public Product(String productCategory, String name, Double price) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
