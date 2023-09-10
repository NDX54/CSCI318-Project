package com.grp7.projectB.domain.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderEvent {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String eventName;

    @Column
    private String orderId;

    public OrderEvent() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "OrderEvent{" +
                "event_name='" + eventName + '\'' +
                ", order_id='" + orderId + '\'' +
                '}';
    }


}
