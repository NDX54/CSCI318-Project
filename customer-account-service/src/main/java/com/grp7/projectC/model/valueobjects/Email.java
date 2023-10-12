package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Email {

    @NotBlank(message = "E-Mail address must not be blank")
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
