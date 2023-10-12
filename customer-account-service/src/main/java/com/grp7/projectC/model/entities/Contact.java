package com.grp7.projectC.model.entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grp7.projectC.model.aggregates.CustomerAggregate;

@Entity
public class Contact {

    @Id
    @Column(name = "contact_id")
    private String contactId;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "phone")
    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @Column(name = "email")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(name = "position")
    @NotBlank(message = "Position cannot be blank")
    private String position;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerAggregate customer;

    public Contact() {}

    public Contact(String contactId, String name, String phone, String email, String position, CustomerAggregate customer) {
        this.contactId = contactId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.customer = customer;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public CustomerAggregate getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerAggregate customer) {
        this.customer = customer;
    }
}
