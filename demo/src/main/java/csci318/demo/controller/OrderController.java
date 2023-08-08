package csci318.demo.controller;

import csci318.demo.repository.OrderRepository;
import csci318.demo.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("/orders")
    Order createOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @PutMapping("/orders/{id}")
    Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        updatedOrder.setId(id);
        return orderRepository.save(updatedOrder);
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
