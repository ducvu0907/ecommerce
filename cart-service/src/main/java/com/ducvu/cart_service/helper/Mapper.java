package com.ducvu.cart_service.helper;

import com.ducvu.cart_service.dto.CartDto;
import com.ducvu.cart_service.dto.CartItemDto;
import com.ducvu.cart_service.entity.Cart;
import com.ducvu.cart_service.entity.CartItem;

public class Mapper {
    public static CartDto map(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .itemDtos(
                        cart.getItems()
                                .stream().map(Mapper::map)
                                .toList()
                )
                .build();
    }

    public static Cart map(CartDto cartDto) {
        return Cart.builder()
                .id(cartDto.getId())
                .userId(cartDto.getUserId())
                .items(
                        cartDto.getItemDtos()
                                .stream().map(Mapper::map)
                                .toList()
                )
                .build();
    }

    public static CartItemDto map(CartItem item) {
        return CartItemDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }

    public static CartItem map(CartItemDto itemDto) {
        return CartItem.builder()
                .id(itemDto.getId())
                .productId(itemDto.getProductId())
                .quantity(itemDto.getQuantity())
                .price(itemDto.getPrice())
                .build();
    }

}
