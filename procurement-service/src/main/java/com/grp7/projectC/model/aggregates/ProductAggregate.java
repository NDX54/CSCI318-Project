package com.grp7.projectC.model.aggregates;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.grp7.projectC.model.valueobjects.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class ProductAggregate extends AbstractAggregateRoot<ProductAggregate> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @EmbeddedId
    @Column(name = "product_id", unique = true, updatable = false)
    @JsonUnwrapped
    private ProductId productId;

    @Embedded
    @Column(name = "product_category", unique = false, updatable = true)
    @Valid
    @JsonUnwrapped
    private ProductCategory productCategory;

    @Embedded
    @Column(name = "name", unique = false, updatable = true)
    @Valid
    @JsonUnwrapped
    private Name name;

    @Embedded
    @Column(name = "price", unique = false, updatable = true)
    @Valid
    @JsonUnwrapped
    private Price price;

    @Embedded
    @Column(name = "description", unique = false, updatable = true)
    @Valid
    @JsonUnwrapped
    private Description description;

    @Embedded
    @Column(name = "comment", unique = false, updatable = true)
    @Valid
    @JsonUnwrapped
    private Comment comment;

    @Embedded
    @Column(name = "stock_remaining", unique = false, updatable = true)
    @Valid
    @JsonUnwrapped
    private Stock stock;


    public ProductId getProductId() { return productId; }

    public void setProductId(ProductId productId) { this.productId = productId; }

    public ProductCategory getProductCategory() { return productCategory; }

    public void setProductCategory(ProductCategory productCategory) { this.productCategory = productCategory; }

    public Name getName() { return name; }

    public void setName(Name name) { this.name = name; }

    public Price getPrice() { return price; }

    public void setPrice(Price price) { this.price = price; }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) { this.description = description; }

    public Comment getComment() { return comment; }

    public void setComment(Comment comment) { this.comment = comment; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "ProductAggregate{" +
                "productId='" + productId + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", name='" + name.toString() + '\'' +
                ", price='" + price.toString() + '\'' +
                ", stock=" + stock.toString() +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
