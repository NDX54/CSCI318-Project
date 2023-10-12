package com.grp7.projectC.model.event;

import javax.persistence.*;
@Entity
public class CustomerEvent {

    public static final String CUSTOMER_CREATED = "CUSTOMER_CREATED";
    public static final String CUSTOMER_UPDATED = "CUSTOMER_UPDATED";
    public static final String CUSTOMER_DELETED = "CUSTOMER_DELETED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일반적인 자동 생성 ID 필드

    private String customerId; // 고객 ID

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;

    private Integer ordersMade = 0;
    private String eventName;

    public CustomerEvent() {}

    public CustomerEvent(String customerId, String customerName, String customerEmail, String customerPhoneNumber, Integer ordersMade, String eventName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.ordersMade = ordersMade;
        this.eventName = eventName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() { return customerPhoneNumber; }

    public void setCustomerPhoneNumber(String customerPhoneNumber) { this.customerPhoneNumber = customerPhoneNumber; }

    public Integer getOrdersMade() {
        return ordersMade;
    }

    public Integer incrementOrdersMade() { return this.ordersMade++; }

    public Integer decrementOrdersMade() { return this.ordersMade--; }

    public void setOrdersMade(Integer ordersMade) {
        this.ordersMade = ordersMade;
    }

    public String getPhone() {
        return customerPhoneNumber;
    }

    public void setPhone(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "CustomerEvent{eventName='" + eventName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", numberOfOrdersMade=" + ordersMade +
                '}';
    }
}

