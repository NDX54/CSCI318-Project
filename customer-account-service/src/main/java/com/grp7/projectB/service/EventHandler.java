package com.grp7.projectB.service;

import com.grp7.projectB.brokers.ContactEventSource;
import com.grp7.projectB.brokers.CustomerEventSource;
import com.grp7.projectB.controller.dto.CustomerEventDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.grp7.projectB.model.event.CustomerCreatedEvent;
import com.grp7.projectB.model.event.ContactCreatedEvent;
import com.grp7.projectB.repository.CustomerCreatedRepository;
import com.grp7.projectB.repository.ContactUpdatedRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Objects;

@Service
@EnableBinding({CustomerEventSource.class, ContactEventSource.class})
public class EventHandler {

    private final CustomerCreatedRepository customerCreatedRepository;
    private final ContactUpdatedRepository contactUpdatedRepository;

    private final CustomerEventSource customerEventSource;
    private final ContactEventSource contactEventSource;

    EventHandler(
            CustomerCreatedRepository customerCreatedRepository,
            ContactUpdatedRepository contactUpdatedRepository,
            CustomerEventSource customerEventSource,
            ContactEventSource contactEventSource
    ) {
        this.customerCreatedRepository = customerCreatedRepository;
        this.contactUpdatedRepository = contactUpdatedRepository;
        this.customerEventSource = customerEventSource;
        this.contactEventSource = contactEventSource;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
        // CustomerCreatedEvent를 처리하는 로직을 작성
        // event를 이용하여 데이터베이스에 저장 또는 필요한 작업 수행
        CustomerEventDTO customerEventDTO = new CustomerEventDTO(event.getCustomerId(),
                event.getCustomerName(),
                event.getCustomerEmail(),
                event.getCustomerPhoneNumber(),
                event.getOrdersMade(),
                event.getEventName()
        );

        if (Objects.equals(event.getEventName(), CustomerCreatedEvent.CUSTOMER_CREATED)) {

            customerEventSource.customerCreation().send(MessageBuilder.withPayload(customerEventDTO).build());

        } else if (Objects.equals(event.getEventName(), CustomerCreatedEvent.CUSTOMER_UPDATED)) {

            customerEventSource.customerUpdate().send(MessageBuilder.withPayload(customerEventDTO).build());

        } else if (Objects.equals(event.getEventName(), CustomerCreatedEvent.CUSTOMER_DELETED)) {

            customerEventSource.customerDeletion().send(MessageBuilder.withPayload(customerEventDTO).build());
        }

        customerCreatedRepository.save(event);
        System.out.println(event);
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleContactUpdatedEvent(ContactCreatedEvent event) {
        // ContactUpdatedEvent를 처리하는 로직을 작성
        // event를 이용하여 데이터베이스에 저장 또는 필요한 작업 수행
        contactUpdatedRepository.save(event);
        System.out.println(event);
    }
}
