package com.grp7.projectB.controller;

import com.grp7.projectB.domain.model.Customer;
import com.grp7.projectB.repository.ContactRepository;
import com.grp7.projectB.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    @Autowired
    CustomerController(CustomerRepository customerRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping()
    List<Customer> all() { return customerRepository.findAll(); }

    @GetMapping("/{customer_id}")
    Customer findCustomerById(@PathVariable long customer_id) { return customerRepository.findById(customer_id).orElseThrow(RuntimeException::new); }

    @PostMapping()
    Customer createCustomer(@RequestBody Customer newCustomer) { return customerRepository.save(newCustomer); }

    @PutMapping("/{customer_id}")
    Customer updateCustomer(@PathVariable Long customer_id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customer_id)
                .orElseThrow(RuntimeException::new);

        updatedCustomer.setId(customer_id);
        updatedCustomer.setCompanyName(customer.getCompanyName());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setCountry(customer.getCountry());
        return customerRepository.save(updatedCustomer);
    }
}
