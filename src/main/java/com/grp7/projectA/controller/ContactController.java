package com.grp7.projectA.controller;

import com.grp7.projectA.model.Contact;
import com.grp7.projectA.model.Customer;
import com.grp7.projectA.repository.ContactRepository;
import com.grp7.projectA.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

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
