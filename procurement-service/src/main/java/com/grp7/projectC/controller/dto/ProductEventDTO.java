package com.grp7.projectC.controller.dto;


import com.grp7.projectC.model.events.ProductEvent;

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

    public ProductEventDTO toAnalyticsFromDTO(ProductEvent productEvent) {
        ProductEventDTO productEventDTO = new ProductEventDTO();
        productEventDTO.setEventName(productEvent.getEventName());
        productEventDTO.setProductName(productEvent.getProductName().toString());
        productEventDTO.setProductPrice(productEvent.getProductPrice().getPrice());
        productEventDTO.setProductCategory(productEvent.getProductCategory().toString());
        productEventDTO.setDescription(productEvent.getDescription().toString());
        productEventDTO.setComment(productEvent.getComment().toString());
        productEventDTO.setStock(productEvent.getStock().getStock());
        productEventDTO.setProductId(productEvent.getProductId().toString());
        return  productEventDTO;
    }

    @Override
    public String toString() {
        return "ProductEventDTO{" +
                "event_name='" + eventName + '\'' +
                ", product_category='" + productCategory + '\'' +
                ", name='" + productName + '\'' +
                ", price='" + productPrice + '\'' +
                ", stock= " + stock.toString() +
                " description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", product_id='" + productId + '\'' +
                '}';
    }
}
