package com.ducvu.cart_service.controller;

import com.ducvu.cart_service.dto.request.AddItemRequest;
import com.ducvu.cart_service.dto.request.AuthRequest;
import com.ducvu.cart_service.dto.request.UpdateItemRequest;
import com.ducvu.cart_service.dto.response.ApiResponse;
import com.ducvu.cart_service.dto.response.CartResponse;
import com.ducvu.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public ApiResponse<CartResponse> getMyCart(@RequestBody AuthRequest request) {
        var res = cartService.getMyCart(request);
        return ApiResponse.<CartResponse>builder().result(res).build();
    }

    @PostMapping("/items")
    public ApiResponse<CartResponse> addItem(@RequestBody AddItemRequest request) {
        var res = cartService.addItem(request);
        return ApiResponse.<CartResponse>builder().result(res).build();
    }

    @PostMapping("/items/{itemId}")
    public ApiResponse<CartResponse> updateItem(@PathVariable("itemId") Integer itemId, @RequestBody UpdateItemRequest request) {
        var res = cartService.updateItem(itemId, request);
        return ApiResponse.<CartResponse>builder().result(res).build();
    }

    @DeleteMapping("/items/{itemId}")
    public ApiResponse<String> deleteItem(@PathVariable("itemId") Integer itemId, @RequestBody AuthRequest request) {
        cartService.deleteItem(itemId, request);
        return ApiResponse.<String>builder().result("Item has been deleted").build();
    }

    @DeleteMapping("/items")
    public ApiResponse<String> deleteItems(@RequestBody AuthRequest request) {
        cartService.deleteItems(request);
        return ApiResponse.<String>builder().result("Cart has been emptied").build();
    }
}
