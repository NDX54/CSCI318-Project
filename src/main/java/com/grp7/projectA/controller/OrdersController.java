package com.grp7.projectA.controller;

import com.grp7.projectA.model.Orders;
import com.grp7.projectA.repository.OrdersRepository;
import com.grp7.projectA.repository.ProductRepository;
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
    List<Orders> all() { return ordersRepository.findAll(); }

    @GetMapping("/{order_id}")
    Orders getOrderByID(@PathVariable Long order_id) { return ordersRepository.findById(order_id).orElseThrow(RuntimeException::new); }

    @PostMapping()
    Orders createOrder(@RequestBody Orders newOrders) { return ordersRepository.save(newOrders); }

    @PutMapping("/{order_id}")
    Orders updateOrder(@PathVariable Long order_id, @RequestBody Orders order) {
        Orders updatedOrder = ordersRepository.findById(order_id).orElseThrow(RuntimeException::new);

        updatedOrder.setId(order_id);
        updatedOrder.setSupplier(order.getSupplier());
        updatedOrder.setProduct(order.getProduct());
        updatedOrder.setQuantity(order.getQuantity());

        return ordersRepository.save(updatedOrder);
    }




}
