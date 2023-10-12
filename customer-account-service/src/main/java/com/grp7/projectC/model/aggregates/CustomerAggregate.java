package com.grp7.projectC.model.aggregates;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.grp7.projectC.model.valueobjects.OrdersMade;
import org.springframework.data.domain.AbstractAggregateRoot;
import com.grp7.projectC.model.entities.Contact;
import com.grp7.projectC.model.valueobjects.Phone;
import com.grp7.projectC.model.valueobjects.Email;
import com.grp7.projectC.model.entities.Address;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerAggregate extends AbstractAggregateRoot<CustomerAggregate> {

    @EmbeddedId
    @Column(name = "customer_id")
    @Valid
    @JsonUnwrapped
    private CustomerId customerId;

    @Column
    @Valid
    @NotBlank(message = "Company name must not be blank")
    private String companyName;

    @Embedded
    @Column(name = "address")
    @Valid
    private Address address;

    @Embedded
    @Column(name = "email")
    @Valid
    private Email email;

    @Embedded
    @Column(name = "phone_number")
    @Valid
    @JsonUnwrapped
    private Phone phone;

    @Embedded
    @Column
    @Valid
    @JsonUnwrapped
    private OrdersMade ordersMade;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    public CustomerAggregate() {}


    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public OrdersMade getOrdersMade() { return ordersMade; }

    public void setOrdersMade(OrdersMade ordersMade) { this.ordersMade = ordersMade; }

//    public Phone getPhoneNumber() {
//        return phone;
//    }
//
//    public void setPhoneNumber(Phone phone) {
//        this.phone = phone;
//    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setCustomer(null);
    }

    // 추가적인 비즈니스 로직 및 메서드를 정의할 수 있습니다
}
