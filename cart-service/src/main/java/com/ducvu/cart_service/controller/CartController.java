package com.ducvu.cart_service.controller;

import com.ducvu.cart_service.dto.CartDto;
import com.ducvu.cart_service.dto.CartItemDto;
import com.ducvu.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    // user
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable("cartId") String cartId) {

    }

    // user
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable("cartId") String cartId, @RequestBody CartItemDto itemDto) {

    }

    // user
    @PostMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable("cartId") String cartId, @RequestBody CartItemDto itemDto) {

    }

    // user
    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> updateItemInCart(@PathVariable("cartId") String cartId, @PathVariable("itemId") String itemId, @RequestBody CartItemDto itemDto) {

    }

    // user
    @PutMapping("/{cartId}")
    public ResponseEntity<Boolean> emptyCart(@PathVariable("cartId") String cartId) {

    }

}
