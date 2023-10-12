package com.grp7.projectC.applicationservice.dto;

public class ProductEventDTO {
    private String eventName;

    private String productName;

    private Double productPrice;

    private String productCategory;

    private String description;

    private String comment;

    private Integer stock;

    private String productId;



    public ProductEventDTO() {}

    public ProductEventDTO(String eventName,
                           String productName,
                           Double productPrice,
                           String productCategory,
                           String description,
                           String comment,
                           Integer stock,
                           String productId
    ) {
        this.eventName = eventName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.description = description;
        this.comment = comment;
        this.stock = stock;
        this.productId = productId;
    }

    public String getEventName() { return eventName; }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public Double getProductPrice() { return productPrice; }

    public void setProductPrice(Double productPrice) { this.productPrice = productPrice; }

    public String getProductCategory() { return productCategory; }

    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public Integer getStock() { return stock; }

    public void setStock(Integer stock) { this.stock = stock; }

    public String getProductId() { return productId; }

    public void setProductId(String productId) { this.productId = productId; }

    @Override
    public String toString() {
        return "ProductEventDTO{" +
                "eventName='" + eventName + '\'' +
                ", productId='" + productId + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", name='" + productName + '\'' +
                ", price='" + productPrice + '\'' +
                ", stock=" + stock + ' ' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
