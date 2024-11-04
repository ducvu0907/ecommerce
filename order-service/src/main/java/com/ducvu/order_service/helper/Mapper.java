package com.ducvu.order_service.helper;

import com.ducvu.order_service.dto.CartDto;
import com.ducvu.order_service.dto.OrderDto;
import com.ducvu.order_service.entity.Cart;
import com.ducvu.order_service.entity.Order;

import java.util.stream.Collectors;

public class Mapper {
    public static OrderDto map(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderDescription(order.getOrderDescription())
                .orderFee(order.getOrderFee())
                .cartDto(Mapper.map(order.getCart()))
                .build();
    }

    public static Order map(OrderDto orderDto) {
        return Order.builder()
                .orderDescription(orderDto.getOrderDescription())
                .orderFee(orderDto.getOrderFee())
                .build();
    }

    public static CartDto map(Cart cart) {
        return CartDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .orderDtos(
                        cart.getOrders()
                                .stream()
                                .map(Mapper::map)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static Cart map(CartDto cartDto) {
        return Cart.builder()
                .userId(cartDto.getUserId())
                .build();
    }
}
