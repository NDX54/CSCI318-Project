package com.grp7.projectB.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Email {

    private String address;

    // Constructors, getters, and setters

    // Constructors
    public Email() {
    }

    public Email(String address) {
        this.address = address;
    }

    // Getters and setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }
}
