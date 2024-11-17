package com.ducvu.cart_service.helper;

import com.ducvu.cart_service.dto.response.CartItemResponse;
import com.ducvu.cart_service.dto.response.CartResponse;
import com.ducvu.cart_service.entity.Cart;
import com.ducvu.cart_service.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(
                        cart.getItems()
                                .stream()
                                .map(this::toCartItemResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }


    public CartItemResponse toCartItemResponse(CartItem item) {
        return CartItemResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .cartId(item.getCart().getId())
                .build();
    }

}
