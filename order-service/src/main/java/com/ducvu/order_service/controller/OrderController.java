package com.ducvu.order_service.controller;

import com.ducvu.order_service.dto.OrderDto;
import com.ducvu.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(Integer userId) {

    }

    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(OrderDto orderDto) {

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderOfUser(@PathVariable String orderId) {

    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable String orderId) {

    }

    // admin
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String orderId, OrderDto orderDto) {

    }
}
