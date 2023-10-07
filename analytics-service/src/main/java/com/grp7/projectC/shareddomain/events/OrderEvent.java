package com.grp7.projectC.shareddomain.events;

public class OrderEvent {

    OrderEventData orderEventData;

    public OrderEvent() {}

    public OrderEvent(OrderEventData orderEventData) {
        this.orderEventData = orderEventData;
    }

    public OrderEventData getOrderEventData() {
        return orderEventData;
    }

    public void setOrderEventData(OrderEventData orderEventData) {
        this.orderEventData = orderEventData;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "orderEventData=" + orderEventData +
                '}';
    }
}
