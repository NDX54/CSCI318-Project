package com.grp7.projectC.controller;

import com.grp7.projectC.customresponses.APIResponse;
import com.grp7.projectC.model.aggregates.OrderAggregate;
import com.grp7.projectC.model.aggregates.OrderId;
import com.grp7.projectC.model.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
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
    @GetMapping("/get")
    ResponseEntity<OrderAggregate> getOrder(@RequestParam("orderId") OrderId orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create-order")
    ResponseEntity<APIResponse<OrderAggregate>> create(@RequestBody OrderAggregate orderAggregate, @RequestParam("customerId") String customerId,
                                  @RequestParam("productId") String productId, WebRequest request) {
        OrderAggregate newOrder = orderService.createOrder(orderAggregate, customerId, productId);

        APIResponse<OrderAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Order created successfully");
        response.setDetails(newOrder);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    ResponseEntity<APIResponse<OrderId>> delete(@RequestParam("orderId") OrderId orderId, WebRequest request) {
        orderService.deleteOrder(orderId);

        APIResponse<OrderId> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Order deleted successfully");
        response.setDetails(orderId);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
