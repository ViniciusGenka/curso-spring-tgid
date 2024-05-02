package com.genka.resources;

import com.genka.domain.order.Order;
import com.genka.dtos.OrderNewDTO;
import com.genka.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping()
    public ResponseEntity<Order> addOrder(@Valid @RequestBody OrderNewDTO orderNewDTO) {
        Order savedOrder = orderService.saveOrder(orderService.mapFromDTO(orderNewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(savedOrder);
    }
}
