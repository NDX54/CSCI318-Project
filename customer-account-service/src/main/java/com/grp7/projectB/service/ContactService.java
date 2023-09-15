package com.grp7.projectB.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grp7.projectB.model.aggregates.CustomerAggregate;
import com.grp7.projectB.model.entities.Contact;
import com.grp7.projectB.repository.CustomerRepository; // 추가
import com.grp7.projectB.repository.ContactRepository;
import com.grp7.projectB.model.event.ContactCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

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

    public Contact getContactById(Long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            return optionalContact.get();
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    @Transactional
    public void createContact(Contact contact, Long customerId) {
        // 연락처(Contact) 객체 생성
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setEmail(contact.getEmail());
        newContact.setPhone(contact.getPhone());
        newContact.setPosition(contact.getPosition());

        // 연관된 고객(Customer) 정보 설정
        CustomerAggregate customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        newContact.setCustomer(customer);

        // 연락처 저장
        contactRepository.save(newContact);

        // ContactUpdatedEvent 이벤트 발행
        ContactCreatedEvent contactCreatedEvent = new ContactCreatedEvent();
        contactCreatedEvent.setContactId(newContact.getId());
        contactCreatedEvent.setUpdatedName(newContact.getName());
        contactCreatedEvent.setUpdatedPhone(newContact.getPhone());
        contactCreatedEvent.setUpdatedEmail(newContact.getEmail());
        contactCreatedEvent.setUpdatedPosition(newContact.getPosition());
        eventPublisher.publishEvent(contactCreatedEvent);
    }



    @Transactional // 트랜잭션 관리를 위한 어노테이션 추가
    public Contact updateContact(Long id, Contact updatedContact) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found");
        }
        updatedContact.setId(id);
        Contact savedContact = contactRepository.save(updatedContact);

        // Contact 업데이트 이벤트를 발행합니다.
        ContactCreatedEvent contactCreatedEvent = new ContactCreatedEvent();
        contactCreatedEvent.setContactId(savedContact.getId());
        contactCreatedEvent.setUpdatedName(savedContact.getName());
        contactCreatedEvent.setUpdatedPhone(savedContact.getPhone());
        contactCreatedEvent.setUpdatedEmail(savedContact.getEmail());
        contactCreatedEvent.setUpdatedPosition(savedContact.getPosition());
        eventPublisher.publishEvent(contactCreatedEvent);

        return savedContact;
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found");
        }
        contactRepository.deleteById(id);
    }
}