package com.ducvu.cart_service.controller;

import com.ducvu.cart_service.dto.request.AddItemRequest;
import com.ducvu.cart_service.dto.request.UpdateItemRequest;
import com.ducvu.cart_service.dto.ApiResponse;
import com.ducvu.cart_service.dto.response.CartResponse;
import com.ducvu.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping("/me")
    public ApiResponse<CartResponse> getMyCart(@RequestHeader("token") String token) {
        var res = cartService.getMyCart(token);
        return ApiResponse.<CartResponse>builder().result(res).build();
    }

    @PostMapping("me/items")
    public ApiResponse<String> addItem(@RequestHeader("token") String token, @RequestBody AddItemRequest request) {
        cartService.addItem(token, request);
        return ApiResponse.<String>builder().result("Item added successfully").build();
    }

    @PutMapping("me/items/{itemId}")
    public ApiResponse<String> updateItem(@RequestHeader("token") String token, @PathVariable("itemId") String itemId, @RequestBody UpdateItemRequest request) {
        cartService.updateItem(token, itemId, request);
        return ApiResponse.<String>builder().result("Item updated successfully").build();
    }

    @DeleteMapping("me/items/{itemId}")
    public ApiResponse<String> deleteItem(@RequestHeader("token") String token, @PathVariable("itemId") String itemId) {
        cartService.deleteItem(token, itemId);
        return ApiResponse.<String>builder().result("Item has been deleted").build();
    }

    @DeleteMapping("me/items")
    public ApiResponse<String> emptyCart(@RequestHeader("token") String token) {
        cartService.emptyCart(token);
        return ApiResponse.<String>builder().result("Cart items has been emptied").build();
    }

}
