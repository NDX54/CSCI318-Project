package com.grp7.projectB.domain.model.entities;

import javax.persistence.Column;

public class Supplier {

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private String supplierId;

    public Supplier() {}

    public Supplier(String supplierId) { this.supplierId = supplierId; }

    public  String getSupplierId() { return this.supplierId; }

    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }


}
