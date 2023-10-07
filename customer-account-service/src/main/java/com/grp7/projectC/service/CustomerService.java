package com.grp7.projectC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grp7.projectC.model.aggregates.CustomerAggregate;
import com.grp7.projectC.model.aggregates.CustomerId;
import com.grp7.projectC.repository.CustomerRepository;
import com.grp7.projectC.model.event.CustomerCreatedEvent;

import javax.persistence.EntityNotFoundException;
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
        customerAggregate.getOrdersMade().setOrdersMade(0);
        customerRepository.save(customerAggregate);

        // 2. CustomerCreatedEvent 이벤트를 생성합니다.
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setEventName(CustomerCreatedEvent.CUSTOMER_CREATED);
        customerCreatedEvent.setCustomerId(customerAggregate.getCustomerId().getCustomerId());
        customerCreatedEvent.setCustomerName(customerAggregate.getCompanyName());
        customerCreatedEvent.setCustomerEmail(customerAggregate.getEmail().getAddress());
        customerCreatedEvent.setPhone(customerAggregate.getPhone().getNumber());
        customerCreatedEvent.setOrdersMade(customerAggregate.getOrdersMade().getOrdersMade());


        // 3. 이벤트를 발행합니다.
        eventPublisher.publishEvent(customerCreatedEvent);
    }

    @Transactional
    public void updateCustomerOrderNumber(CustomerId customerId) {
        CustomerAggregate customer = customerRepository.findCustomerByCustomerId(customerId).orElseThrow(EntityNotFoundException::new);

//        Integer incrementedOrderMadeByCustomer = customer.getOrdersMade().incrementOrdersMade();

//        System.out.println("FROM updateCustomerOrderNumber: " + incrementedOrderMadeByCustomer);
        customer.getOrdersMade().incrementOrdersMade();
        CustomerCreatedEvent customerUpdatedEvent = new CustomerCreatedEvent();
        customerUpdatedEvent.setEventName(CustomerCreatedEvent.CUSTOMER_UPDATED);
        customerUpdatedEvent.setCustomerId(customer.getCustomerId().getCustomerId());
        customerUpdatedEvent.setCustomerName(customer.getCompanyName());
        customerUpdatedEvent.setCustomerEmail(customer.getEmail().getAddress());
        customerUpdatedEvent.setCustomerPhoneNumber(customer.getPhone().getNumber());
        customerUpdatedEvent.incrementOrdersMade();
        System.out.println("FROM updateCustomerOrderNumber: " + customerUpdatedEvent.getOrdersMade());

        eventPublisher.publishEvent(customerUpdatedEvent);

        customerRepository.save(customer);
    }

    @Transactional
    public CustomerAggregate updateCustomer(Long customerId, CustomerAggregate updatedCustomerAggregate) {
        Optional<CustomerAggregate> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            CustomerAggregate customerToUpdate = existingCustomer.get();
            // 여기에 업데이트 로직을 추가하세요.
            // 예를 들어, customerToUpdate.setCompanyName(updatedCustomerAggregate.getCompanyName());
            CustomerCreatedEvent customerUpdatedEvent = new CustomerCreatedEvent();
            customerUpdatedEvent.setEventName(CustomerCreatedEvent.CUSTOMER_UPDATED);
            customerUpdatedEvent.setCustomerId(customerToUpdate.getCustomerId().getCustomerId());
            customerUpdatedEvent.setCustomerName(customerToUpdate.getCompanyName());
            customerUpdatedEvent.setCustomerEmail(customerToUpdate.getEmail().getAddress());
            customerUpdatedEvent.setCustomerPhoneNumber(customerToUpdate.getPhone().getNumber());
            customerUpdatedEvent.setOrdersMade(customerToUpdate.getOrdersMade().getOrdersMade());

            eventPublisher.publishEvent(customerUpdatedEvent);

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
