package com.grp7.projectC.shareddomain.events;

public class CustomerEventData {
    private String customerId; // 고객 ID

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;

    private Integer ordersMade;
    private String eventName;

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
        return "CustomerEventData{" +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", numberOfOrdersMade=" + ordersMade +
                '}';
    }
}
