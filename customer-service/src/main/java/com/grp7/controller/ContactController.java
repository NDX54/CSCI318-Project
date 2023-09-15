package com.grp7.controller;

import com.grp7.model.Contact;
import com.grp7.model.Customer;
import com.grp7.repository.ContactRepository;
import com.grp7.repository.CustomerRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    public ContactController(ContactRepository contactRepository, CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customers/{customer_id}/contacts")
    Contact createCustomerContact(@PathVariable Long customer_id, @RequestBody Contact contact) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(RuntimeException::new);
        contact.setCustomer(customer);
        customer.getContacts().add(contact);

        return contactRepository.save(contact);
    }


}
