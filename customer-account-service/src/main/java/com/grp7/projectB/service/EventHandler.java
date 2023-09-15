package com.grp7.projectB.service;

import org.springframework.stereotype.Service;
import com.grp7.projectB.model.event.CustomerCreatedEvent;
import com.grp7.projectB.model.event.ContactCreatedEvent;
import com.grp7.projectB.repository.CustomerCreatedRepository;
import com.grp7.projectB.repository.ContactUpdatedRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final CustomerCreatedRepository customerCreatedRepository;
    private final ContactUpdatedRepository contactUpdatedRepository;

    EventHandler(
            CustomerCreatedRepository customerCreatedRepository,
            ContactUpdatedRepository contactUpdatedRepository
    ) {
        this.customerCreatedRepository = customerCreatedRepository;
        this.contactUpdatedRepository = contactUpdatedRepository;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
        // CustomerCreatedEvent를 처리하는 로직을 작성
        // event를 이용하여 데이터베이스에 저장 또는 필요한 작업 수행
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
