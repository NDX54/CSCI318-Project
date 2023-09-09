package com.grp7.projectB.domain.model.aggregates;

import javax.persistence.*;

import com.grp7.projectB.domain.model.entities.Supplier;
import com.grp7.projectB.domain.model.valueObjects.Product;
import com.grp7.projectB.domain.model.valueObjects.Quantity;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private OrderId orderId;
    @Embedded
    private Supplier supplier;

    @Embedded
    private Product product;

//    @Column
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<Product> product = new ArrayList<>();

//    @Column
//    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
//    private List<LineItem> lineItems;

    @Embedded
    private Quantity quantity;

    public Order() {

    }
    public Order(Supplier supplier, Product product, Quantity quantity) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quantity getQuantity() { return quantity; }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
