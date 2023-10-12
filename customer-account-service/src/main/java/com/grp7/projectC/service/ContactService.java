package com.grp7.projectC.service;

import com.grp7.projectC.errorhandlers.CustomNotFoundException;
import com.grp7.projectC.model.aggregates.CustomerId;
import com.grp7.projectC.model.event.ContactEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grp7.projectC.model.aggregates.CustomerAggregate;
import com.grp7.projectC.model.entities.Contact;
import com.grp7.projectC.repository.CustomerRepository; // 추가
import com.grp7.projectC.repository.ContactRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository; // 추가
    private final ApplicationEventPublisher eventPublisher; // 이벤트 발행자


    public ContactService(
            ContactRepository contactRepository,
            CustomerRepository customerRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactByContactId(String contactId) {
        Optional<Contact> optionalContact = contactRepository.findByContactId(contactId);
        if (optionalContact.isPresent()) {
            return optionalContact.get();
        } else {
            throw new CustomNotFoundException("Contact not found");
        }
    }

    @Transactional
    public Contact createContact(Contact contact, CustomerId customerId) {
        // 연락처(Contact) 객체 생성
        String random = UUID.randomUUID().toString().toUpperCase();
        String contactIdStr = random.substring(0, random.indexOf("-"));
        contact.setContactId(contactIdStr);

        // 연관된 고객(Customer) 정보 설정
        CustomerAggregate customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomNotFoundException("Customer not found"));
        contact.setCustomer(customer);



        // ContactUpdatedEvent 이벤트 발행
        ContactEvent contactEvent = new ContactEvent();
        contactEvent.setEventName(ContactEvent.CONTACT_CREATED);
        contactEvent.setContactId(contact.getContactId());
        contactEvent.setName(contact.getName());
        contactEvent.setPhone(contact.getPhone());
        contactEvent.setEmail(contact.getEmail());
        contactEvent.setPosition(contact.getPosition());
        eventPublisher.publishEvent(contactEvent);

        // 연락처 저장
        return contactRepository.save(contact);
    }

    @Transactional
    public Contact updateContact(CustomerId customerId, String contactId, Contact updatedContact) {
        CustomerAggregate customerAggregate = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomNotFoundException("Customer not found"));

        // 본문에서 필요한 로직을 추가하세요.

        // 예시: 본문에서 필요한 로직을 추가한 예시
        Contact contactToUpdate = customerAggregate.getContacts().stream()
                .filter(contact -> Objects.equals(contact.getContactId(), contactId))
                .findFirst()
                .orElseThrow(() -> new CustomNotFoundException("Contact not found"));

        contactToUpdate.setName(updatedContact.getName());
        contactToUpdate.setPhone(updatedContact.getPhone());
        contactToUpdate.setEmail(updatedContact.getEmail());
        contactToUpdate.setPosition(updatedContact.getPosition());

        ContactEvent contactUpdatedEvent = new ContactEvent();
        contactUpdatedEvent.setEventName(ContactEvent.CONTACT_UPDATED);
        contactUpdatedEvent.setContactId(contactId);
        contactUpdatedEvent.setName(contactToUpdate.getName());
        contactUpdatedEvent.setPhone(contactToUpdate.getPhone());
        contactUpdatedEvent.setEmail(contactToUpdate.getEmail());
        contactUpdatedEvent.setPosition(contactToUpdate.getPosition());

        eventPublisher.publishEvent(contactUpdatedEvent);

        return contactRepository.save(contactToUpdate);
    }

    public void deleteContact(String contactId) {
        Contact contactToDelete = contactRepository.findByContactId(contactId).orElseThrow(() -> new CustomNotFoundException("Contact not found"));

        ContactEvent contactEvent = new ContactEvent();
        contactEvent.setEventName(ContactEvent.CONTACT_DELETED);
        contactEvent.setContactId(contactToDelete.getContactId());
        contactEvent.setName(contactToDelete.getName());
        contactEvent.setPhone(contactToDelete.getPhone());
        contactEvent.setEmail(contactToDelete.getEmail());
        contactEvent.setPosition(contactToDelete.getPosition());
        eventPublisher.publishEvent(contactEvent);

        contactRepository.delete(contactToDelete);
    }
}