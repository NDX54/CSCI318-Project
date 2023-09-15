package com.grp7.projectB.model.services;


import com.grp7.projectB.model.aggregates.OrderAggregate;
import com.grp7.projectB.model.aggregates.OrderId;
import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.events.ProductEvent;
import com.grp7.projectB.repository.OrderEventRepository;
import com.grp7.projectB.repository.OrderRepository;
import com.grp7.projectB.repository.ProductEventRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

//    private final OrderEventRepository orderEventRepository;
    private final RestTemplate restTemplate;

    private final ApplicationEventPublisher applicationEventPublisher;

    OrderService(OrderRepository orderRepository,
                 OrderEventRepository orderEventRepository,
                 RestTemplate restTemplate,
                 ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
//        this.orderEventRepository = orderEventRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<OrderAggregate> getAllProducts() { return orderRepository.findAll(); }

    public OrderAggregate getProduct(OrderId orderId) { return orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new); }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createOrder(OrderAggregate newOrderAggregate) {

        String random = UUID.randomUUID().toString().toUpperCase();
        String orderIdStr = random.substring(0, random.indexOf("-"));
        newOrderAggregate.setOrderId(new OrderId(orderIdStr));

        orderRepository.save(newOrderAggregate);


//        ProductEvent productEvent = new ProductEvent();
//
//        productEvent.setEventName("Create");
//        productEvent.setProductId(new ProductId(orderIdStr));
//        productEvent.setProductCategory(newOrderAggregate.getProductCategory());
//        productEvent.setProductName(newOrderAggregate.getName());
//        productEvent.setProductPrice(newOrderAggregate.getPrice());
//        productEvent.setDescription(newOrderAggregate.getDescription());
//        productEvent.setComment(newOrderAggregate.getComment());
//
//        applicationEventPublisher.publishEvent(productEvent);

    }

//    @Transactional
//    public void updateProduct(OrderId orderId, OrderAggregate orderAggregate) {
//        OrderAggregate existingOrderAggregate = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
//        existingOrderAggregate.se(orderAggregate.getProductCategory());
//        existingOrderAggregate.setName(orderAggregate.getName());
//        existingOrderAggregate.setPrice(orderAggregate.getPrice());
//        existingOrderAggregate.setDescription(orderAggregate.getDescription());
//        existingOrderAggregate.setComment(orderAggregate.getComment());
//
//
//        productRepository.save(existingOrderAggregate);
//
//
////        ProductEvent productEvent = new ProductEvent();
////
////        productEvent.setEventName("Update");
////        productEvent.setProductId(orderId);
////        productEvent.setProductCategory(orderAggregate.getProductCategory());
////        productEvent.setProductName(orderAggregate.getName());
////        productEvent.setProductPrice(orderAggregate.getPrice());
////        productEvent.setDescription(orderAggregate.getDescription());
////        productEvent.setComment(orderAggregate.getComment());
////
////        applicationEventPublisher.publishEvent(productEvent);
//    }

//    @Transactional
//    public void deleteProduct(ProductId productId) {
//        ProductAggregate productToDelete = productRepository.findByProductId(productId).orElseThrow(EntityNotFoundException::new);
//
//        ProductEvent productEvent = new ProductEvent();
//
//        productEvent.setEventName("Delete");
//        productEvent.setProductId(productId);
//        productEvent.setProductCategory(productToDelete.getProductCategory());
//        productEvent.setProductName(productToDelete.getName());
//        productEvent.setProductPrice(productToDelete.getPrice());
//        productEvent.setDescription(productToDelete.getDescription());
//        productEvent.setComment(productToDelete.getComment());
//
//        applicationEventPublisher.publishEvent(productEvent);
//
//        productRepository.deleteByProductId(productId);
//
//    }

}
