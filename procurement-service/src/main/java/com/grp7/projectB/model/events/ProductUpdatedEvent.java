package com.grp7.projectB.model.events;

import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.valueobjects.Name;
import com.grp7.projectB.model.valueobjects.Price;
import com.grp7.projectB.model.valueobjects.ProductCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductUpdatedEvent {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String eventName;

    @Column
    private Name productName;

    @Column
    private Price productPrice;

    @Column
    private ProductCategory productCategory;

    @Column
    private ProductId productId;

    public ProductUpdatedEvent() {}

    public ProductUpdatedEvent(String eventName) { this.eventName = eventName; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getEventName() { return eventName; }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public Name getProductName() { return productName; }

    public void setProductName(Name productName) { this.productName = productName; }

    public Price getProductPrice() { return productPrice; }

    public void setProductPrice(Price productPrice) { this.productPrice = productPrice; }

    public ProductCategory getProductCategory() { return productCategory; }

    public void setProductCategory(ProductCategory productCategory) { this.productCategory = productCategory; }

    public ProductId getProductId() { return productId; }

    public void setProductId(ProductId productId) { this.productId = productId; }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "event_name='" + eventName.toString() + '\'' +
                ", product_category='" + productCategory.toString() + '\'' +
                ", name='" + productName.toString() + '\'' +
                ", price='" + productPrice.toString() + '\'' +
                ", product_id='" + productId.toString() + '\'' +
                '}';
    }
}
