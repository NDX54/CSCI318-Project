package com.grp7.projectB.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Phone {

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
