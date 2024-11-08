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

    // user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable("userId") Integer userId) {

    }

    // user
    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

    }

    // user
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderOfUser(@PathVariable("orderId") String orderId) {

    }

    // user
    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable("orderId") String orderId) {

    }

    // admin
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") String orderId, @RequestBody OrderDto orderDto) {

    }
}
