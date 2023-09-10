package com.grp7.projectB.model;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String supplier;

    @Column
    private String product;

//    @Column
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<Product> product = new ArrayList<>();

//    @Column
//    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
//    private List<LineItem> lineItems;

    @Column
    private int quantity;

    public Orders() {

    }
    public Orders(String supplier, String product, int quantity) {
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
