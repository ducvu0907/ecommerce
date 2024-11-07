package com.ducvu.cart_service.service;

import com.ducvu.cart_service.dto.CartDto;
import com.ducvu.cart_service.dto.CartItemDto;
import com.ducvu.cart_service.repository.CartItemRepository;
import com.ducvu.cart_service.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartDto getCartById(Integer cartId) {

    }

    public CartDto addItem(Integer cartId, CartItemDto itemDto) {

    }

    public CartDto removeItem(Integer cartId, Integer itemId) {

    }

    public CartDto updateItem(Integer cartId, Integer itemId) {

    }

    public void deleteCartItems(Integer cartId) {

    }
}
