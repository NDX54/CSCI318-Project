package com.grp7.projectB.model.event;;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContactCreatedEvent {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String eventName;
    @Column
    private Long contactId;
    @Column
    private String updatedName;
    @Column
    private String updatedPhone;
    @Column
    private String updatedEmail;
    @Column
    private String updatedPosition;

    public ContactCreatedEvent() {
        this.eventName = "ContactCreatedEvent"; // 이벤트 이름을 설정합니다.
    }

    public String getEventName() {
        return eventName;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public String getUpdatedPhone() {
        return updatedPhone;
    }

    public void setUpdatedPhone(String updatedPhone) {
        this.updatedPhone = updatedPhone;
    }

    public String getUpdatedEmail() {
        return updatedEmail;
    }

    public void setUpdatedEmail(String updatedEmail) {
        this.updatedEmail = updatedEmail;
    }

    public String getUpdatedPosition() {
        return updatedPosition;
    }

    public void setUpdatedPosition(String updatedPosition) {
        this.updatedPosition = updatedPosition;
    }

    @Override
    public String toString() {
        return "ContactCreatedEvent{" +
                "event_name='" + eventName + '\'' +
                ", contact_id=" + contactId +
                ", updated_name='" + updatedName + '\'' +
                ", updated_phone='" + updatedPhone + '\'' +
                ", updated_email='" + updatedEmail + '\'' +
                ", updated_position='" + updatedPosition + '\'' +
                '}';
    }
}
