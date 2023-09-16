package com.grp7.projectB.controller;

import com.grp7.projectB.model.aggregates.CustomerAggregate;
import com.grp7.projectB.model.aggregates.CustomerId;
import com.grp7.projectB.model.entities.Contact;
import com.grp7.projectB.repository.ContactRepository;
import com.grp7.projectB.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import com.grp7.projectB.service.CustomerService;

import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, ContactRepository contactRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
        this.customerService = customerService;
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerAggregate customerAggregate) {
        // CustomerAggregate에서 필요한 정보를 추출하거나 매핑하여 Customer 엔터티를 생성하고 저장합니다.
        customerService.createCustomer(customerAggregate);

        // Contact 엔터티에 연결된 Customer 정보를 설정하고 저장합니다.
        for (Contact contact : customerAggregate.getContacts()) {
            contact.setCustomer(customerAggregate);
            contactRepository.save(contact);
        }
    }

    @PutMapping("/update/{id}")
    public CustomerAggregate updateCustomer(@PathVariable Long id, @RequestBody CustomerAggregate updatedCustomerAggregate) {
        CustomerAggregate existingCustomerAggregate = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 본문에서 필요한 로직을 추가하세요.

        // 예시: 본문에서 필요한 로직을 추가한 예시
        existingCustomerAggregate.setCompanyName(updatedCustomerAggregate.getCompanyName());
        existingCustomerAggregate.setAddress(updatedCustomerAggregate.getAddress());
        // ...

        // Contact 엔터티를 업데이트합니다.
        for (Contact updatedContact : updatedCustomerAggregate.getContacts()) {
            Contact existingContact = contactRepository.findById(updatedContact.getId()).orElseThrow();
            existingContact.setName(updatedContact.getName());
            existingContact.setPhone(updatedContact.getPhone());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setPosition(updatedContact.getPosition());
            contactRepository.save(existingContact);
        }

        return customerRepository.save(existingCustomerAggregate);
    }

    @PostMapping("/{customer_id}/contacts")
    public Contact createCustomerContact(@PathVariable Long customer_id, @RequestBody Contact contact) {
        CustomerAggregate customerAggregate = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 중복된 연락처 검사
        boolean isContactExists = customerAggregate.getContacts().stream()
                .anyMatch(existingContact -> existingContact.getEmail().equals(contact.getEmail()));

        if (isContactExists) {
            throw new RuntimeException("Contact already exists");
        }

        contact.setCustomer(customerAggregate);  // 연락처 정보와 고객 정보 연결
        customerAggregate.addContact(contact);

        return contact;
    }

    @PutMapping("/{customer_id}/contacts/{contact_id}")
    public Contact updateCustomerContact(
            @PathVariable Long customer_id,
            @PathVariable Long contact_id,
            @RequestBody Contact updatedContact
    ) {
        CustomerAggregate customerAggregate = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 본문에서 필요한 로직을 추가하세요.

        // 예시: 본문에서 필요한 로직을 추가한 예시
        Contact contactToUpdate = customerAggregate.getContacts().stream()
                .filter(contact -> contact.getId() == contact_id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contactToUpdate.setName(updatedContact.getName());
        contactToUpdate.setPhone(updatedContact.getPhone());
        contactToUpdate.setEmail(updatedContact.getEmail());
        contactToUpdate.setPosition(updatedContact.getPosition());

        return contactToUpdate;
    }

    @GetMapping
    public List<CustomerAggregate> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public CustomerAggregate getCustomerById(@PathVariable CustomerId id) {
        return customerRepository.findCustomerByCustomerId(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
