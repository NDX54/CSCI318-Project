package com.grp7.projectB.controller;

import com.grp7.projectB.controller.dto.OrderDTO;
import com.grp7.projectB.model.aggregates.OrderAggregate;
import com.grp7.projectB.model.aggregates.OrderId;
import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.services.OrderService;
import com.grp7.projectB.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private final RestTemplate restTemplate;
//    private final ProductRepository productRepository;

    @Autowired
    OrdersController(OrderRepository orderRepository, OrderService orderService, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.restTemplate = restTemplate;
//        this.productRepository = productRepository;
    }

    @GetMapping
    List<OrderAggregate> all() { return orderRepository.findAll(); }

    @GetMapping("/{order_id}")
    OrderAggregate getOrderByID(@PathVariable OrderId order_id) { return orderRepository.findByOrderId(order_id).orElseThrow(RuntimeException::new); }

//    @PostMapping()
//    OrderAggregate createOrder(@RequestBody OrderAggregate newOrders) {
//
//
//        return orderRepository.save(newOrders);
//    }

    @PostMapping()
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            // Replace "product-service-base-url" with the actual URL of the product service
            String productServiceUrl = "http://localhost:8082/products/" + orderDTO.getProductId().getProductId();
            ResponseEntity<ProductAggregate> productResponse = restTemplate.getForEntity(productServiceUrl, ProductAggregate.class);

            if (productResponse.getStatusCode() == HttpStatus.OK) {
                // Product found, create the order with product details
                String random = UUID.randomUUID().toString().toUpperCase();
                String orderIdStr = random.substring(0, random.indexOf("-"));
                OrderAggregate order = new OrderAggregate();
//                order.setOrderId(new OrderId(orderIdStr));
                order.setProduct(productResponse.getBody());
                order.setQuantity(orderDTO.getQuantity());
//                orderService.createOrder(order);
                orderRepository.save(order);

                ResponseEntity.ok("Order created successfully");
            } else {
                // Product not found, handle the error
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
        } catch (RestClientException e) {
            // Handle communication errors with the product service
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error communicating with the product service");
        }
    }


    @PutMapping("/{order_id}")
    OrderAggregate updateOrder(@PathVariable OrderId order_id, @RequestBody OrderAggregate orderAggregate) {
        OrderAggregate updatedOrder = orderRepository.findByOrderId(order_id).orElseThrow(RuntimeException::new);

//        updatedOrder.setId(order_id);
        updatedOrder.setSupplier(orderAggregate.getSupplier());
        updatedOrder.setProduct(orderAggregate.getProduct());
        updatedOrder.setQuantity(orderAggregate.getQuantity());

        return orderRepository.save(updatedOrder);
    }

}
