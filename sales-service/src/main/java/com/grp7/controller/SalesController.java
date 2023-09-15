package com.grp7.controller;

import com.grp7.model.aggregates.OrderAggregate;
import com.grp7.model.aggregates.OrderId;
import com.grp7.model.services.OrderService;
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
    public ResponseEntity<List<OrderAggregate>> getAllOrders() {
        List<OrderAggregate> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Get a specific order
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderAggregate> getOrder(@PathVariable OrderId orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new order
//    @PostMapping
//    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDto) {
//        OrderAggregate order = orderService.createOrder(orderDto).getOrderAggregate();
//        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
//    }
    @PostMapping
    public void create(@RequestBody OrderAggregate orderAggregate) {
        orderService.createOrder(orderAggregate);
//        OrderAggregate order = orderService.createOrder(orderDto).getOrderAggregate();
//        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public void update(@PathVariable OrderId orderId, @RequestBody OrderAggregate orderAggregate) { orderService.updateOrder(orderId, orderAggregate); }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable OrderId orderId) { orderService.deleteOrder(orderId); }

    // Update an existing order
//    @PutMapping("/{orderId}")
//    public ResponseEntity<OrderDTO> updateOrder(@PathVariable UUID orderId, @RequestBody OrderDTO orderDto) {
//        if (orderService.updateOrder(orderId, orderDto).isPresent()) {
//            return new ResponseEntity<>(orderDto, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete an order
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
//        orderService.deleteOrder(orderId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
