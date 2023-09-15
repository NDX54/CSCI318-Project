package com.grp7.model.services;

import com.grp7.model.aggregates.OrderAggregate;
import com.grp7.model.aggregates.OrderId;
import com.grp7.model.events.OrderEvent;
import com.grp7.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

//    @Transactional
//    public OrderCreatedEvent createOrder(OrderDTO orderDto) {
//        OrderAggregate order = new OrderAggregate(orderDto.getSupplier(), orderDto.getProduct(), new Quantity(orderDto.getQuantity().getAmount()));
//        orderRepository.save(order);
//        logger.info("Order created with ID: {}", order.getOrderId());
//        return new OrderCreatedEvent(order.getOrderId(), orderDto.getSupplier(), orderDto.getProduct(), orderDto.getQuantity());
//    }
    @Transactional
    public void createOrder(OrderAggregate orderAggregate) {
        String random = UUID.randomUUID().toString().toUpperCase();
        String orderIdStr = random.substring(0, random.indexOf("-"));
        orderAggregate.setOrderId(new OrderId(orderIdStr));
        orderRepository.save(orderAggregate);

        OrderEvent orderCreatedEvent = new OrderEvent(
                new OrderId(orderIdStr),
                orderAggregate.getSupplier(),
                orderAggregate.getProduct(),
                orderAggregate.getQuantity()
        );
        orderCreatedEvent.setEventName("Create");

        applicationEventPublisher.publishEvent(orderCreatedEvent);

        logger.info("Order created with ID: {}", orderAggregate.getOrderId());
    }

    @Transactional
    public void updateOrder(OrderId orderId, OrderAggregate orderAggregate) {
            OrderAggregate existingOrder = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
            existingOrder.setProduct(orderAggregate.getProduct());
            existingOrder.setSupplier(orderAggregate.getSupplier());
            existingOrder.setQuantity(orderAggregate.getQuantity());

            orderRepository.save(existingOrder);

            OrderEvent orderUpdatedEvent = new OrderEvent(
                    orderId,
                    orderAggregate.getSupplier(),
                    orderAggregate.getProduct(),
                    orderAggregate.getQuantity()
            );
            orderUpdatedEvent.setEventName("Update");

            applicationEventPublisher.publishEvent(orderUpdatedEvent);
    }

    @Transactional
    public void deleteOrder(OrderId orderId) {

        OrderAggregate orderToDelete = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);

        OrderEvent orderDeletedEvent = new OrderEvent(
                orderId,
                orderToDelete.getSupplier(),
                orderToDelete.getProduct(),
                orderToDelete.getQuantity()
        );
        orderDeletedEvent.setEventName("Delete");

        applicationEventPublisher.publishEvent(orderDeletedEvent);

        orderRepository.deleteByOrderId(orderId);
    }

//    @Transactional
//    public Optional<OrderUpdatedEvent> updateOrder(UUID orderId, OrderDTO orderDto) {
//        Optional<OrderAggregate> optionalOrder = orderRepository.findById(orderId);
//        if (optionalOrder.isPresent()) {
//            OrderAggregate order = optionalOrder.get();
//            order.updateOrder(orderDto.getSupplier(), orderDto.getProduct(), new Quantity(orderDto.getQuantity().getAmount()));
//            orderRepository.save(order);
//            logger.info("Order updated with ID: {}", orderId);
//            return Optional.of(new OrderUpdatedEvent(orderId, orderDto.getSupplier(), orderDto.getProduct(), orderDto.getQuantity()));
//        }
//        logger.warn("Order with ID: {} not found for update.", orderId);
//        return Optional.empty();
//    }
//
//    @Transactional
//    public void deleteOrder(UUID orderId) {
//        if (orderRepository.existsById(orderId)) {
//            orderRepository.deleteById(orderId);
//            logger.info("Order deleted with ID: {}", orderId);
//            // This event could be dispatched to some event bus if required
//            new OrderDeletedEvent(orderId);
//        } else {
//            logger.warn("Order with ID: {} not found for deletion.", orderId);
//        }
//    }

    public List<OrderAggregate> getAllOrders() {
        List<OrderAggregate> orders = new ArrayList<>(orderRepository.findAll());
        return orders;
    }

    public Optional<OrderAggregate> getOrderById(OrderId orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
