package com.ducvu.order_service.controller;

import com.ducvu.order_service.dto.request.CreateOrderRequest;
import com.ducvu.order_service.dto.ApiResponse;
import com.ducvu.order_service.dto.response.OrderResponse;
import com.ducvu.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ApiResponse<List<OrderResponse>> getMyOrders(@RequestHeader("token") String token) {
        var res = orderService.getMyOrders(token);
        return ApiResponse.<List<OrderResponse>>builder().result(res).build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrder(@RequestHeader("token") String token, @PathVariable("orderId") String orderId) {
        var res = orderService.getOrder(token, orderId);
        return ApiResponse.<OrderResponse>builder().result(res).build();
    }

    @PostMapping("")
    public ApiResponse<OrderResponse> createOrder(@RequestHeader("token") String token, @RequestBody CreateOrderRequest request) {
        var res = orderService.createOrder(token, request);
        return ApiResponse.<OrderResponse>builder().result(res).build();
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<String> cancelOrder(@RequestHeader("token") String token, @PathVariable("orderId") String orderId) {
        orderService.cancelOrder(token, orderId);
        return ApiResponse.<String>builder().result("Order has been cancelled").build();
    }

}