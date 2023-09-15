package com.grp7.projectB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grp7.projectB.model.aggregates.CustomerAggregate;
import com.grp7.projectB.model.aggregates.CustomerId;
import com.grp7.projectB.repository.CustomerRepository;
import com.grp7.projectB.model.event.CustomerCreatedEvent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public CustomerService(
            CustomerRepository customerRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void createCustomer(CustomerAggregate customerAggregate) {
        // 1. 고객을 저장하고 반환합니다.
        String random = UUID.randomUUID().toString().toUpperCase();
        String customerIdStr = random.substring(0, random.indexOf("-"));
        customerAggregate.setCustomerId(new CustomerId(customerIdStr));
        customerRepository.save(customerAggregate);

        // 2. CustomerCreatedEvent 이벤트를 생성합니다.
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setEventName("Created");
        customerCreatedEvent.setCustomerId(customerAggregate.getCustomerId().getCustomerId());
        customerCreatedEvent.setCustomerName(customerAggregate.getCompanyName());
        customerCreatedEvent.setCustomerEmail(customerAggregate.getEmail().getAddress());
        customerCreatedEvent.setPhone(customerAggregate.getPhone().getNumber());

        // 3. 이벤트를 발행합니다.
        eventPublisher.publishEvent(customerCreatedEvent);
    }



    public CustomerAggregate updateCustomer(Long customerId, CustomerAggregate updatedCustomerAggregate) {
        Optional<CustomerAggregate> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            CustomerAggregate customerToUpdate = existingCustomer.get();
            // 여기에 업데이트 로직을 추가하세요.
            // 예를 들어, customerToUpdate.setCompanyName(updatedCustomerAggregate.getCompanyName());
            return customerRepository.save(customerToUpdate);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public List<CustomerAggregate> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerAggregate getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // 다른 비즈니스 로직 메서드를 추가할 수 있습니다.
}
