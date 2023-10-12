package com.grp7.projectC.service;

import com.grp7.projectC.brokers.ContactEventSource;
import com.grp7.projectC.brokers.CustomerEventSource;
import com.grp7.projectC.controller.dto.CustomerEventDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.grp7.projectC.model.event.CustomerEvent;
import com.grp7.projectC.model.event.ContactEvent;
import com.grp7.projectC.repository.CustomerEventRepository;
import com.grp7.projectC.repository.ContactEventRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Objects;

@Service
@EnableBinding({CustomerEventSource.class, ContactEventSource.class})
public class CustomerAccountServiceEventHandler {

    private final CustomerEventRepository customerEventRepository;
    private final ContactEventRepository contactEventRepository;

    private final CustomerEventSource customerEventSource;
    private final ContactEventSource contactEventSource;

    CustomerAccountServiceEventHandler(
            CustomerEventRepository customerEventRepository,
            ContactEventRepository contactEventRepository,
            CustomerEventSource customerEventSource,
            ContactEventSource contactEventSource
    ) {
        this.customerEventRepository = customerEventRepository;
        this.contactEventRepository = contactEventRepository;
        this.customerEventSource = customerEventSource;
        this.contactEventSource = contactEventSource;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCustomerEvent(CustomerEvent event) {
        // CustomerCreatedEvent를 처리하는 로직을 작성
        // event를 이용하여 데이터베이스에 저장 또는 필요한 작업 수행
        CustomerEventDTO customerEventDTO = new CustomerEventDTO(event.getCustomerId(),
                event.getCustomerName(),
                event.getCustomerEmail(),
                event.getCustomerPhoneNumber(),
                event.getOrdersMade(),
                event.getEventName()
        );

//        System.out.println(customerEventDTO.getOrdersMade());

        if (Objects.equals(event.getEventName(), CustomerEvent.CUSTOMER_CREATED)) {

            customerEventSource.customerCreation().send(MessageBuilder.withPayload(customerEventDTO).build());

        } else if (Objects.equals(event.getEventName(), CustomerEvent.CUSTOMER_UPDATED)) {

            customerEventSource.customerUpdate().send(MessageBuilder.withPayload(customerEventDTO).build());

        } else if (Objects.equals(event.getEventName(), CustomerEvent.CUSTOMER_DELETED)) {

            customerEventSource.customerDeletion().send(MessageBuilder.withPayload(customerEventDTO).build());
        }

        customerEventRepository.save(event);
        System.out.println(event);
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleContactEvent(ContactEvent event) {
        // ContactUpdatedEvent를 처리하는 로직을 작성
        // event를 이용하여 데이터베이스에 저장 또는 필요한 작업 수행
        contactEventRepository.save(event);
        System.out.println(event);
    }
}
