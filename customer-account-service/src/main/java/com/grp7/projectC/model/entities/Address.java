package com.grp7.projectC.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {

    @Column(name = "street_address")
    @NotBlank(message = "Address must not be blank")
    private String streetAddress;

    @Column(name = "city")
    @NotBlank(message = "City must not be blank")
    private String city;

    @Column(name = "postal_code")
    @NotBlank(message = "Postal code must not be blank")
    private String postalCode;

    @Column(name = "country")
    @NotBlank(message = "Country must not be blank")
    private String country;

    public Address() {
    }

    public Address(String streetAddress, String city, String postalCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
