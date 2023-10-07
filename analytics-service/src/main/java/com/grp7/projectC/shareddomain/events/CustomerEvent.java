package com.grp7.projectC.shareddomain.events;

public class CustomerEvent {

    CustomerEventData customerEventData;

    CustomerEvent() {}

    CustomerEvent(CustomerEventData customerEventData) { this.customerEventData = customerEventData; }

    public CustomerEventData getCustomerEventData() { return customerEventData; }

    public void setCustomerEventData(CustomerEventData customerEventData) { this.customerEventData = customerEventData; }

    @Override
    public String toString() {
        return "CustomerEvent{" +
                "customerEventData=" + customerEventData +
                '}';
    }
}
