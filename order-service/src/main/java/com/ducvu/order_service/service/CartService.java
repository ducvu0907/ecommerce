package com.ducvu.order_service.service;

import com.ducvu.order_service.dto.CartDto;
import com.ducvu.order_service.entity.Cart;
import com.ducvu.order_service.exception.CartNotFoundException;
import com.ducvu.order_service.helper.Mapper;
import com.ducvu.order_service.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public CartDto getCartById(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(String.format("Cart with id: %d not found", cartId)));

        return Mapper.map(cart);
    }

    public CartDto createCart(CartDto cartDto) {
        return Mapper.map(this.cartRepository.save(Mapper.map(cartDto)));
    }


}
