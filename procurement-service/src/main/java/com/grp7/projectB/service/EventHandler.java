package com.grp7.projectB.service;

import com.grp7.projectB.model.events.ProductEvent;
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
    @Transactional
    public void handleProductEvent(ProductEvent productEvent) {
        productEventRepository.save(productEvent);
        System.out.println(productEvent);
    }

//    @TransactionalEventListener
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void handleUpdateEvent(ProductEvent productUpdatedEvent) {
//        productEventRepository.save(productUpdatedEvent);
//        System.out.println(productUpdatedEvent);
//    }

}
