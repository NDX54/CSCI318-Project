package com.grp7.projectC.model.event;;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContactEvent {

    public static final String CONTACT_CREATED = "CONTACT_CREATED";
    public static final String CONTACT_UPDATED = "CONTACT_UPDATED";
    public static final String CONTACT_DELETED = "CONTACT_DELETED";

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String eventName;
    @Column
    private String contactId;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String position;

    public ContactEvent() {}

    public ContactEvent(long id, String eventName, String contactId, String name, String phone, String email, String position) {
        this.id = id;
        this.eventName = eventName;
        this.contactId = contactId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String updatedName) {
        this.name = updatedName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String updatedPhone) {
        this.phone = updatedPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String updatedEmail) {
        this.email = updatedEmail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String updatedPosition) {
        this.position = updatedPosition;
    }

    @Override
    public String toString() {
        return "ContactEvent{" +
                "eventName='" + eventName + '\'' +
                ", contactId=" + contactId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
