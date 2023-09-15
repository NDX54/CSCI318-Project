package com.grp7.projectB.model.aggregates;

import org.springframework.data.domain.AbstractAggregateRoot;
import com.grp7.projectB.model.aggregates.CustomerId;
import com.grp7.projectB.model.entities.Contact;
import com.grp7.projectB.model.valueobjects.Phone;
import com.grp7.projectB.model.valueobjects.Email;
import com.grp7.projectB.model.entities.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerAggregate extends AbstractAggregateRoot<CustomerAggregate> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 설정
    private Long id; // 자동 생성되는 customerId

    @Column
    private String companyName;

    @Embedded
    private CustomerId customerId;

    @Embedded
    private Address address;

    @Embedded
    private Email email;

    @Embedded
    private Phone phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    public CustomerAggregate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long customerId) {
        this.id = customerId;
    }


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
