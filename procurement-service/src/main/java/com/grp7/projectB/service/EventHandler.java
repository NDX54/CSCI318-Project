package com.grp7.projectB.service;

import com.grp7.projectB.model.events.ProductCreatedEvent;
import com.grp7.projectB.repository.ProductEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final ProductEventRepository productEventRepository;

    EventHandler(ProductEventRepository productEventRepository) { this.productEventRepository = productEventRepository; }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCreateEvent(ProductCreatedEvent productCreatedEvent) {
        productEventRepository.save(productCreatedEvent);
        System.out.println(productCreatedEvent);
    }


}
