package com.grp7.projectC.shareddomain.events;

public class OrderEventData {

    private String eventName;


    private String orderId;


    private String customerId;


    private String productId;


    private String supplier;


    private String product;


    private Integer quantity;

    // No-args constructor for JPA
    public OrderEventData() {
    }

    public OrderEventData(String orderId, String customerId, String productId, String supplier, String product, Integer quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderEventData{" +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", product='" + product + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
