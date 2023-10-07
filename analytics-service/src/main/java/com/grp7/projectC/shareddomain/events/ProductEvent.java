package com.grp7.projectC.shareddomain.events;

public class ProductEvent {

    ProductEventData productEventData;

    public ProductEvent() {}

    public ProductEvent(ProductEventData productEventData) { this.productEventData = productEventData; }

    public ProductEventData getProductEventData() { return productEventData; }

    public void setProductEventData(ProductEventData productEventData) { this.productEventData = productEventData; }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "productEventData=" + productEventData +
                '}';
    }
}
