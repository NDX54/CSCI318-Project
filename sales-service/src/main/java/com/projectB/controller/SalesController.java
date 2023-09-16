package com.projectB.controller;

import com.grp7.projectB.model.aggregates.CustomerId;
import com.grp7.projectB.model.aggregates.ProductId;
import com.projectB.model.aggregates.OrderAggregate;
import com.projectB.model.aggregates.OrderId;
import com.projectB.model.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final OrderService orderService;

    public SalesController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @GetMapping
    ResponseEntity<List<OrderAggregate>> getAllOrders() {
        List<OrderAggregate> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Get a specific order
    @GetMapping("/{orderId}")
    ResponseEntity<OrderAggregate> getOrder(@PathVariable OrderId orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/create-order/{customerId}/{productId}")
    void create(@RequestBody OrderAggregate orderAggregate, @PathVariable CustomerId customerId, @PathVariable ProductId productId) {
        orderService.createOrder(orderAggregate, customerId, productId);
    }

    @PutMapping("/update-order/{orderId}/product-id/{productId}")
    void update(@RequestBody OrderAggregate orderAggregate,
                @PathVariable OrderId orderId,
                @PathVariable ProductId productId
                ) { orderService.updateOrder(orderAggregate, orderId, productId); }

    @DeleteMapping("/delete/{orderId}")
    void delete(@PathVariable OrderId orderId) { orderService.deleteOrder(orderId); }

}
