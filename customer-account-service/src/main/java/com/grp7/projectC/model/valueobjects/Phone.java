package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Phone {

    @NotBlank(message = "Phone number must not be blank")
    private String number;

    // Constructors, getters, and setters

    // Constructors
    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }

    // Getters and setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }
}
