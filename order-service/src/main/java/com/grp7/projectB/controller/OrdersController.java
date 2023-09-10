package com.grp7.projectB.controller;

import com.grp7.projectB.domain.model.aggregates.Order;
import com.grp7.projectB.repository.OrdersRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Autowired
    OrdersController(OrdersRepository ordersRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    List<Order> all() { return ordersRepository.findAll(); }

    @GetMapping("/{order_id}")
    Order getOrderByID(@PathVariable Long order_id) { return ordersRepository.findById(order_id).orElseThrow(RuntimeException::new); }

    @PostMapping()
    Order createOrder(@RequestBody Order newOrder) { return ordersRepository.save(newOrder); }

    @PutMapping("/{order_id}")
    Order updateOrder(@PathVariable Long order_id, @RequestBody Order order) {
        Order updatedOrder = ordersRepository.findById(order_id).orElseThrow(RuntimeException::new);

        updatedOrder.setId(order_id);
//        updatedOrder.setOrderId(order_id);
        updatedOrder.setSupplier(order.getSupplier());
//        updatedOrder.setProduct(order.getProduct());
        updatedOrder.setQuantity(order.getQuantity());

        return ordersRepository.save(updatedOrder);
    }




}
