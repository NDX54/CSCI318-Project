package csci318.demo.controller;

import csci318.demo.repository.CustomerRepository;
import csci318.demo.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/customers")
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PutMapping("/customers/{id}")
    Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        updatedCustomer.setId(id);
        return customerRepository.save(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
