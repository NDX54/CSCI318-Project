package com.grp7.projectC.service;

import com.grp7.projectC.errorhandlers.CustomNotFoundException;
import com.grp7.projectC.model.entities.Contact;
import com.grp7.projectC.model.event.ContactEvent;
import com.grp7.projectC.model.event.CustomerEvent;
import com.grp7.projectC.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grp7.projectC.model.aggregates.CustomerAggregate;
import com.grp7.projectC.model.aggregates.CustomerId;
import com.grp7.projectC.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ContactRepository contactRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public CustomerService(
            CustomerRepository customerRepository,
            ContactRepository contactRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public CustomerAggregate createCustomer(CustomerAggregate customerAggregate) {
        // 1. 고객을 저장하고 반환합니다.
        String random = UUID.randomUUID().toString().toUpperCase();
        String customerIdStr = random.substring(0, random.indexOf("-"));
        customerAggregate.setCustomerId(new CustomerId(customerIdStr));
//        customerAggregate.getOrdersMade().setOrdersMade(0);


        // 2. CustomerEvent 이벤트를 생성합니다.
        CustomerEvent customerEvent = new CustomerEvent();
        customerEvent.setEventName(CustomerEvent.CUSTOMER_CREATED);
        customerEvent.setCustomerId(customerAggregate.getCustomerId().getCustomerId());
        customerEvent.setCustomerName(customerAggregate.getCompanyName());
        customerEvent.setCustomerEmail(customerAggregate.getEmail().getAddress());
        customerEvent.setPhone(customerAggregate.getPhone().getNumber());
        customerEvent.setOrdersMade(customerAggregate.getOrdersMade().getOrdersMade());


        // 3. 이벤트를 발행합니다.
        eventPublisher.publishEvent(customerEvent);

        return customerRepository.save(customerAggregate);
    }

    @Transactional
    public CustomerAggregate addCustomerOrderNumber(CustomerId customerId) {
        CustomerAggregate customer = customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomNotFoundException("Customer not found"));

        customer.getOrdersMade().incrementOrdersMade();
        CustomerEvent customerUpdatedEvent = new CustomerEvent();
        customerUpdatedEvent.setEventName(CustomerEvent.CUSTOMER_UPDATED);
        customerUpdatedEvent.setCustomerId(customer.getCustomerId().getCustomerId());
        customerUpdatedEvent.setCustomerName(customer.getCompanyName());
        customerUpdatedEvent.setCustomerEmail(customer.getEmail().getAddress());
        customerUpdatedEvent.setCustomerPhoneNumber(customer.getPhone().getNumber());
        customerUpdatedEvent.incrementOrdersMade();
        System.out.println("FROM updateCustomerOrderNumber: " + customerUpdatedEvent.getOrdersMade());

        eventPublisher.publishEvent(customerUpdatedEvent);

        return customerRepository.save(customer);
    }

    @Transactional
    public CustomerAggregate subtractCustomerOrderNumber(CustomerId customerId) {
        CustomerAggregate customer = customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomNotFoundException("Customer not found"));


        customer.getOrdersMade().decrementOrdersMade();
        CustomerEvent customerUpdatedEvent = new CustomerEvent();
        customerUpdatedEvent.setEventName(CustomerEvent.CUSTOMER_UPDATED);
        customerUpdatedEvent.setCustomerId(customer.getCustomerId().getCustomerId());
        customerUpdatedEvent.setCustomerName(customer.getCompanyName());
        customerUpdatedEvent.setCustomerEmail(customer.getEmail().getAddress());
        customerUpdatedEvent.setCustomerPhoneNumber(customer.getPhone().getNumber());
        customerUpdatedEvent.decrementOrdersMade();
        System.out.println("FROM updateCustomerOrderNumber: " + customerUpdatedEvent.getOrdersMade());

        eventPublisher.publishEvent(customerUpdatedEvent);

        return customerRepository.save(customer);
    }

    @Transactional
    public CustomerAggregate updateCustomer(CustomerId customerId, CustomerAggregate updatedCustomerAggregate) {
        CustomerAggregate existingCustomer = customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomNotFoundException("Customer not found"));

        existingCustomer.setCompanyName(updatedCustomerAggregate.getCompanyName());
        existingCustomer.setAddress(updatedCustomerAggregate.getAddress());
        existingCustomer.setEmail(updatedCustomerAggregate.getEmail());
        existingCustomer.getContacts().addAll(updatedCustomerAggregate.getContacts());
        existingCustomer.setPhone(updatedCustomerAggregate.getPhone());
        existingCustomer.setOrdersMade(updatedCustomerAggregate.getOrdersMade());

        // 여기에 업데이트 로직을 추가하세요.
        // 예를 들어, customerToUpdate.setCompanyName(updatedCustomerAggregate.getCompanyName());
        CustomerEvent customerUpdatedEvent = new CustomerEvent();
        customerUpdatedEvent.setEventName(CustomerEvent.CUSTOMER_UPDATED);
        customerUpdatedEvent.setCustomerId(existingCustomer.getCustomerId().getCustomerId());
        customerUpdatedEvent.setCustomerName(existingCustomer.getCompanyName());
        customerUpdatedEvent.setCustomerEmail(existingCustomer.getEmail().getAddress());
        customerUpdatedEvent.setCustomerPhoneNumber(existingCustomer.getPhone().getNumber());
        customerUpdatedEvent.setOrdersMade(existingCustomer.getOrdersMade().getOrdersMade());

        eventPublisher.publishEvent(customerUpdatedEvent);

        return customerRepository.save(existingCustomer);
    }

    @Transactional
    public void deleteCustomer(CustomerId customerId) {
        CustomerAggregate customerToDelete = customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomNotFoundException("Customer not found"));

        List<Contact> contactToDelete = contactRepository.findAllByCustomerId(customerId.toString());

        contactRepository.deleteAll(contactToDelete);

        CustomerEvent customerDeletedEvent = new CustomerEvent();
        customerDeletedEvent.setEventName(CustomerEvent.CUSTOMER_DELETED);
        customerDeletedEvent.setCustomerId(customerToDelete.getCustomerId().getCustomerId());
        customerDeletedEvent.setCustomerName(customerToDelete.getCompanyName());
        customerDeletedEvent.setCustomerEmail(customerToDelete.getEmail().getAddress());
        customerDeletedEvent.setCustomerPhoneNumber(customerToDelete.getPhone().getNumber());
        customerDeletedEvent.setOrdersMade(customerToDelete.getOrdersMade().getOrdersMade());

        eventPublisher.publishEvent(customerDeletedEvent);

        customerRepository.delete(customerToDelete);
    }

    public List<CustomerAggregate> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerAggregate findCustomer(CustomerId customerId) {
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomNotFoundException("Customer not found"));
    }

    // 다른 비즈니스 로직 메서드를 추가할 수 있습니다.
}
