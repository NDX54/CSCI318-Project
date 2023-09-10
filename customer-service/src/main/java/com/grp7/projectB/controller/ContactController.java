package com.grp7.projectB.controller;

import com.grp7.projectB.model.Contact;
import com.grp7.projectB.model.Customer;
import com.grp7.projectB.repository.ContactRepository;
import com.grp7.projectB.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    @Autowired
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

    @PutMapping("/customers/{customer_id}/contacts/{contact_id}")
    Contact updateCustomerContact(@PathVariable Long customer_id,@PathVariable Long contact_id ,@RequestBody Contact contact) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(RuntimeException::new);
        Contact updatedContact = contactRepository.findById(contact_id).orElseThrow(RuntimeException::new);

        updatedContact.setCustomer(customer);
        updatedContact.setId(contact_id);
        updatedContact.setName(contact.getName());
        updatedContact.setPhone(contact.getPhone());
        updatedContact.setEmail(contact.getEmail());
        updatedContact.setPosition(contact.getPosition());
        return contactRepository.save(updatedContact);
    }

}
