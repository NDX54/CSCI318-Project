package com.projectB.model.services;

import com.projectB.brokers.OrderEventSource;
import com.projectB.controller.OrderEventDTO;
import com.projectB.model.events.OrderEvent;
import com.projectB.repository.OrderEventRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Objects;

@Service
@EnableBinding(OrderEventSource.class)
public class EventHandler {

    private final OrderEventRepository orderEventRepository;

    OrderEventSource orderEventSource;

    public EventHandler(OrderEventRepository orderEventRepository, OrderEventSource orderEventSource) {
        this.orderEventRepository = orderEventRepository;
        this.orderEventSource = orderEventSource;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleOrderEvent(OrderEvent orderEvent) {
        OrderEventDTO orderEventDTO = new OrderEventDTO(
                orderEvent.getEventName(),
                orderEvent.getOrderId(),
                orderEvent.getCustomerId(),
                orderEvent.getSupplier(),
                orderEvent.getProduct(),
                orderEvent.getQuantity().getQuantity()
        );

        if (Objects.equals(orderEvent.getEventName(), OrderEvent.ORDER_CREATED)) {

            orderEventSource.orderCreation().send(MessageBuilder.withPayload(orderEventDTO).build());

        } else if (Objects.equals(orderEvent.getEventName(), OrderEvent.ORDER_DELETED)) {

            orderEventSource.orderDeletion().send(MessageBuilder.withPayload(orderEventDTO).build());

        }

        orderEventRepository.save(orderEvent);
    }
}
