package com.grp7.projectC.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grp7.projectC.errorhandlers.InsufficientStockException;
import com.grp7.projectC.errorhandlers.WrongQuantityException;
import com.grp7.projectC.model.aggregates.OrderAggregate;
import com.grp7.projectC.model.aggregates.OrderId;
import com.grp7.projectC.repository.OrderRepository;
import com.grp7.projectC.model.events.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public OrderAggregate createOrder(OrderAggregate orderAggregate, String customerId, String productId) {
        String random = UUID.randomUUID().toString().toUpperCase();
        String orderIdStr = random.substring(0, random.indexOf("-"));

        final String customerURL = "http://localhost:8081/customers/";
        final String productURL = "http://localhost:8082/products/";
        ResponseEntity<String> response1 = restTemplate.getForEntity(customerURL + customerId, String.class);
        ResponseEntity<String> response2 = restTemplate.getForEntity(productURL + "/get/" + productId, String.class);
        String customerResponse = response1.getBody();
        String productResponse = response2.getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode customerJsonNode = objectMapper.readTree(customerResponse);
            JsonNode productJsonNode = objectMapper.readTree(productResponse);
            String customerIdFromJson = customerJsonNode.get("customerId").asText();
            String productIdFromJson = productJsonNode.get("productId").asText();
            String productNameFromJson = productJsonNode.get("name").asText();
            Integer productStockFromJson = productJsonNode.get("stock").asInt();

            orderAggregate.setCustomerId(customerIdFromJson);
            orderAggregate.setProductId(productIdFromJson);
            orderAggregate.setProduct(productNameFromJson);

            Integer orderQuantity = orderAggregate.getQuantity().getQuantity();

            if (productStockFromJson < orderQuantity) {
                throw new InsufficientStockException("Insufficient stock");
            } else if (orderQuantity <= 0) {
                throw new WrongQuantityException("Quantity must be at least 1");
            }

            ObjectNode updatedProductNode = productJsonNode.deepCopy();
            updatedProductNode.put("stock", productStockFromJson - orderQuantity);

            restTemplate.put(customerURL + "/add-order-made-number/" + customerIdFromJson, null);
            restTemplate.put(productURL + "/update/" + productIdFromJson, updatedProductNode);

            orderAggregate.setOrderId(new OrderId(orderIdStr));

            OrderEvent orderCreatedEvent = new OrderEvent();

            orderCreatedEvent.setEventName(OrderEvent.ORDER_CREATED);
            orderCreatedEvent.setCustomerId(customerIdFromJson);
            orderCreatedEvent.setOrderId(orderIdStr);
            orderCreatedEvent.setProductId(productIdFromJson);
            orderCreatedEvent.setSupplier(orderAggregate.getSupplier());
            orderCreatedEvent.setProduct(orderAggregate.getProduct());
            orderCreatedEvent.setQuantity(orderAggregate.getQuantity());

            applicationEventPublisher.publishEvent(orderCreatedEvent);

            logger.info("Order created with ID: {}", orderAggregate.getOrderId());

            return orderRepository.save(orderAggregate);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    @Transactional
    public void deleteOrder(OrderId orderId) {

        OrderAggregate orderToDelete = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);

        final String customerURL = "http://localhost:8081/customers/";
        final String productURL = "http://localhost:8082/products/";
        ResponseEntity<String> response1 = restTemplate.getForEntity(customerURL + orderToDelete.getCustomerId(), String.class);
        ResponseEntity<String> response2 = restTemplate.getForEntity(productURL + "/get/" + orderToDelete.getProductId(), String.class);
        String customerResponse = response1.getBody();
        String productResponse = response2.getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode customerJsonNode = objectMapper.readTree(customerResponse);
            JsonNode productJsonNode = objectMapper.readTree(productResponse);
            String customerIdFromJson = customerJsonNode.get("customerId").asText();
            String productIdFromJson = productJsonNode.get("productId").asText();
            Integer productStockFromJson = productJsonNode.get("stock").asInt();

            Integer orderQuantity = orderToDelete.getQuantity().getQuantity();
            ObjectNode updatedProductNode = productJsonNode.deepCopy();
            updatedProductNode.put("stock", productStockFromJson + orderQuantity);

            restTemplate.put(customerURL + "/subtract-order-made-number/" + customerIdFromJson, null);
            restTemplate.put(productURL + "/update/" + productIdFromJson, updatedProductNode);

            OrderEvent orderDeletedEvent = new OrderEvent();
            orderDeletedEvent.setEventName(OrderEvent.ORDER_DELETED);
            orderDeletedEvent.setCustomerId(orderToDelete.getCustomerId());
            orderDeletedEvent.setProductId(orderToDelete.getProductId());
            orderDeletedEvent.setOrderId(orderToDelete.getOrderId().toString());
            orderDeletedEvent.setSupplier(orderToDelete.getSupplier());
            orderDeletedEvent.setProduct(orderToDelete.getProduct());
            orderDeletedEvent.setQuantity(orderToDelete.getQuantity());

            applicationEventPublisher.publishEvent(orderDeletedEvent);

            orderRepository.deleteByOrderId(orderId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<OrderAggregate> getAllOrders() {
        return new ArrayList<>(orderRepository.findAll());
    }

    public Optional<OrderAggregate> getOrderById(OrderId orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
