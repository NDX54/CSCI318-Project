package com.grp7.projectC.model.events;

import com.grp7.projectC.model.aggregates.ProductId;
import com.grp7.projectC.model.valueobjects.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductEvent {

    public static final String PRODUCT_CREATED = "PRODUCT_CREATED";
    public static final String PRODUCT_UPDATED = "PRODUCT_UPDATED";
    public static final String PRODUCT_DELETED = "PRODUCT_DELETED";

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
    private Description description;

    @Column
    private Comment comment;

    @Column
    private Stock stock;

    @Column
    private ProductId productId;



    public ProductEvent() {}

    public ProductEvent(String eventName) { this.eventName = eventName; }

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

    public Description getDescription() { return description; }

    public void setDescription(Description description) { this.description = description; }

    public Comment getComment() { return comment; }

    public void setComment(Comment comment) { this.comment = comment; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    public ProductId getProductId() { return productId; }

    public void setProductId(ProductId productId) { this.productId = productId; }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "event_name='" + eventName.toString() + '\'' +
                ", product_category='" + productCategory.toString() + '\'' +
                ", name='" + productName.toString() + '\'' +
                ", price='" + productPrice.toString() + '\'' +
                stock.toString() +
                " description='" + description.toString() + '\'' +
                ", comment='" + comment.toString() + '\'' +
                ", product_id='" + productId.toString() + '\'' +
                '}';
    }
}
