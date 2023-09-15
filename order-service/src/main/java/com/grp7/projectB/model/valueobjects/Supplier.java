package com.grp7.projectB.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Supplier {

    @Column(name = "supplier_name", unique = true, updatable = true)
    private String supplier;

    public Supplier() {}

    public Supplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
