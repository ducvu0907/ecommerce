package com.ducvu.order_service.controller;

import com.ducvu.order_service.dto.request.AuthRequest;
import com.ducvu.order_service.dto.request.CreateOrderRequest;
import com.ducvu.order_service.dto.response.ApiResponse;
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

    // buyer
    @PostMapping("")
    public ApiResponse<List<OrderResponse>> getMyOrders(@RequestBody AuthRequest request) {
        var res = orderService.getMyOrders(request);
        return ApiResponse.<List<OrderResponse>>builder().result(res).build();
    }

    // buyer
    @PostMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable("orderId") Integer orderId, @RequestBody AuthRequest request) {
        var res = orderService.getOrder(orderId, request);
        return ApiResponse.<OrderResponse>builder().result(res).build();
    }

    // buyer
    @PostMapping("/create")
    public ApiResponse<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        var res = orderService.createOrder(request);
        return ApiResponse.<OrderResponse>builder().result(res).build();
    }

    // buyer
    @DeleteMapping("/{orderId}")
    public ApiResponse<String> cancelOrder(@PathVariable("orderId") Integer orderId, @RequestBody AuthRequest request) {
        orderService.cancelOrder(orderId, request);
        return ApiResponse.<String>builder().result("Order has been cancelled").build();
    }

}