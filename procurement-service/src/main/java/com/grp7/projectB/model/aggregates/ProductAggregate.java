package com.grp7.projectB.model.aggregates;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.grp7.projectB.model.valueobjects.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
public class ProductAggregate extends AbstractAggregateRoot<ProductAggregate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @JsonUnwrapped
    private ProductId productId;

    @Embedded
    @JsonUnwrapped
    private ProductCategory productCategory;

    @Embedded
    @JsonUnwrapped
    private Name name;

    @Embedded
    @JsonUnwrapped
    private Price price;

    @Embedded
    @JsonUnwrapped
    private Description description;

    @Embedded
    @JsonUnwrapped
    private Comment comment;


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

}
