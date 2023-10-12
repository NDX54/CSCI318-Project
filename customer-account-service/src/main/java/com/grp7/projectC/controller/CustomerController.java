package com.grp7.projectC.controller;

import com.grp7.projectC.customresponses.APIResponse;
import com.grp7.projectC.errorhandlers.CustomNotFoundException;
import com.grp7.projectC.model.aggregates.CustomerAggregate;
import com.grp7.projectC.model.aggregates.CustomerId;
import com.grp7.projectC.model.entities.Contact;
import com.grp7.projectC.repository.ContactRepository;
import com.grp7.projectC.repository.CustomerRepository;
import com.grp7.projectC.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grp7.projectC.service.CustomerService;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @GetMapping
    public List<CustomerAggregate> getAllCustomers() { return customerService.getAllCustomers(); }

    @GetMapping("/{customerId}")
    public CustomerAggregate getCustomerById(@PathVariable CustomerId customerId) { return customerService.findCustomer(customerId);}

    @PostMapping
    public ResponseEntity<APIResponse<CustomerAggregate>> createCustomer(@Valid @RequestBody CustomerAggregate customerAggregate, WebRequest request) {
        // CustomerAggregate에서 필요한 정보를 추출하거나 매핑하여 Customer 엔터티를 생성하고 저장합니다.
        CustomerAggregate newCustomer = customerService.createCustomer(customerAggregate);

        // Contact 엔터티에 연결된 Customer 정보를 설정하고 저장합니다.
        for (Contact contact : customerAggregate.getContacts()) {
            contact.setCustomer(customerAggregate);
            contactRepository.save(contact);
        }

        APIResponse<CustomerAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Customer created successfully");
        response.setDetails(newCustomer);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<APIResponse<CustomerAggregate>> updateCustomer(@PathVariable CustomerId customerId, @Valid @RequestBody CustomerAggregate updatedCustomerAggregate, WebRequest request) {
        CustomerAggregate updatedCustomer = customerService.updateCustomer(customerId, updatedCustomerAggregate);

        APIResponse<CustomerAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Customer updated successfully");
        response.setDetails(updatedCustomer);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{customerId}")
    public ResponseEntity<APIResponse<CustomerId>> deleteCustomer(@PathVariable CustomerId customerId, WebRequest request) {
        customerService.deleteCustomer(customerId);

        APIResponse<CustomerId> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Customer deleted successfully");
        response.setDetails(customerId);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/add-order-made-number/{customerId}")
    public ResponseEntity<APIResponse<CustomerAggregate>> addOrderMadeNumber(@PathVariable CustomerId customerId, WebRequest request) {
        CustomerAggregate updatedCustomer = customerService.addCustomerOrderNumber(customerId);

        APIResponse<CustomerAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Number of order incremented for customer");
        response.setDetails(updatedCustomer);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/subtract-order-made-number/{customerId}")
    public ResponseEntity<APIResponse<CustomerAggregate>> subtractOrderMadeNumber(@PathVariable CustomerId customerId, WebRequest request) {
        CustomerAggregate updatedCustomer = customerService.subtractCustomerOrderNumber(customerId);

        APIResponse<CustomerAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Number of order decremented for customer");
        response.setDetails(updatedCustomer);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
