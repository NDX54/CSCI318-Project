package com.grp7.projectB.model.aggregates;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.grp7.projectB.model.valueobjects.Product;
import com.grp7.projectB.model.valueobjects.Supplier;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
public class OrderAggregate extends AbstractAggregateRoot<OrderAggregate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @JsonUnwrapped
    private OrderId orderId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductAggregate product;

    @Embedded
    @JsonUnwrapped
    private Supplier supplier;

    @Column
    private int quantity;

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductAggregate getProduct() {
        return product;
    }

    public void setProduct(ProductAggregate product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
