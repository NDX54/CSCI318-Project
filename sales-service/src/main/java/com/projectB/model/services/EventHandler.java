package com.projectB.model.services;

import com.projectB.model.events.OrderEvent;
import com.projectB.repository.OrderEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    private final OrderEventRepository orderEventRepository;

    public EventHandler(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleOrderEvent(OrderEvent orderEvent) {
        orderEventRepository.save(orderEvent);
        System.out.println(orderEvent);
    }
}
