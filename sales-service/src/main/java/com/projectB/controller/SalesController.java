package com.projectB.controller;

import com.projectB.model.aggregates.OrderAggregate;
import com.projectB.model.aggregates.OrderId;
import com.projectB.model.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sales/orders")
public class SalesController {

    @Autowired
    private OrderService orderService;

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
    @PostMapping
    void create(@RequestBody OrderAggregate orderAggregate) {
        orderService.createOrder(orderAggregate);
    }

    @PutMapping("/{orderId}")
    void update(@PathVariable OrderId orderId, @RequestBody OrderAggregate orderAggregate) { orderService.updateOrder(orderId, orderAggregate); }

    @DeleteMapping("/{orderId}")
    void delete(@PathVariable OrderId orderId) { orderService.deleteOrder(orderId); }

}
