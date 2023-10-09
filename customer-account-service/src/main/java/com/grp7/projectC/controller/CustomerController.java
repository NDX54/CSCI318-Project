package com.grp7.projectC.controller;

import com.grp7.projectC.model.aggregates.CustomerAggregate;
import com.grp7.projectC.model.aggregates.CustomerId;
import com.grp7.projectC.model.entities.Contact;
import com.grp7.projectC.repository.ContactRepository;
import com.grp7.projectC.repository.CustomerRepository;
import com.grp7.projectC.service.ContactService;
import org.springframework.web.bind.annotation.*;
import com.grp7.projectC.service.CustomerService;

import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    private final ContactService contactService;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, ContactRepository contactRepository, ContactService contactService, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
        this.contactService = contactService;
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

    @PutMapping("/update/{customerId}")
    public CustomerAggregate updateCustomer(@PathVariable CustomerId customerId, @RequestBody CustomerAggregate updatedCustomerAggregate) {
        CustomerAggregate existingCustomerAggregate = customerRepository.findByCustomerId(customerId)
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

    @PutMapping("/add-order-made-number/{customerId}")
    public void addOrderMadeNumber(@PathVariable CustomerId customerId) {
        customerService.addCustomerOrderNumber(customerId);
    }

    @PutMapping("/subtract-order-made-number/{customerId}")
    public void subtractOrderMadeNumber(@PathVariable CustomerId customerId) {
        customerService.subtractCustomerOrderNumber(customerId);
    }


    @PostMapping("/{customerId}/contacts")
    public Contact createCustomerContact(@PathVariable Long customerId, @RequestBody Contact contact) {
        CustomerAggregate customerAggregate = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 중복된 연락처 검사
        boolean contactExists = customerAggregate.getContacts().stream()
                .anyMatch(existingContact -> existingContact.getEmail().equals(contact.getEmail()));

        if (contactExists) {
            throw new RuntimeException("Contact already exists");
        }

        contact.setCustomer(customerAggregate);  // 연락처 정보와 고객 정보 연결
        customerAggregate.addContact(contact);

        return contact;
    }

    @PutMapping("/{customerId}/contacts/{contactId}")
    public void updateCustomerContact(
            @PathVariable CustomerId customerId,
            @PathVariable Long contactId,
            @RequestBody Contact updatedContact
    ) {

        customerService.updateCustomerContact(customerId,contactId,updatedContact);
    }

    @GetMapping
    public List<CustomerAggregate> getAllCustomers() { return customerService.getAllCustomers(); }

    @GetMapping("/{customerId}")
    public CustomerAggregate getCustomerById(@PathVariable CustomerId customerId) { return customerService.findCustomer(customerId);}
}
