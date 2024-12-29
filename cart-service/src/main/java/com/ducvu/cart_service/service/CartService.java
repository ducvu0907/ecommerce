package com.ducvu.cart_service.service;

import com.ducvu.cart_service.config.ProductClient;
import com.ducvu.cart_service.config.UserClient;
import com.ducvu.cart_service.dto.request.*;
import com.ducvu.cart_service.dto.response.AuthResponse;
import com.ducvu.cart_service.dto.response.CartItemResponse;
import com.ducvu.cart_service.dto.response.CartResponse;
import com.ducvu.cart_service.entity.Cart;
import com.ducvu.cart_service.entity.CartItem;
import com.ducvu.cart_service.helper.Mapper;
import com.ducvu.cart_service.repository.CartItemRepository;
import com.ducvu.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public CartResponse getMyCart(String token) {
        log.info("Cart service; Get my cart");

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .userId(userId)
                            .build();
                    return cartRepository.save(newCart);
                });

        List<CartItemResponse> cartItemResponses = cart.getItems().stream()
                .map(item -> {
                    var productResponse = productClient.getProduct(item.getProductId());
                    double subtotal = productResponse.getResult().getPrice() * item.getQuantity();
                    return CartItemResponse.builder()
                            .productId(item.getProductId())
                            .quantity(item.getQuantity())
                            .subtotal(subtotal)
                            .build();
                })
                .toList();

        return CartResponse.builder()
                .id(cart.getId())
                .userId(userId)
                .items(cartItemResponses)
                .build();
    }

    @Transactional
    public void addItem(String token, AddItemRequest request) {
        log.info("Cart service; Add item");

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .userId(userId)
                            .build();
                    return cartRepository.save(newCart);
                });

        var productResponse = productClient.getProduct(request.getProductId());
        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }

        if (request.getQuantity() > productResponse.getResult().getQuantity()) {
            throw new RuntimeException("Quantity exceeded");
        }

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(request.getProductId()))
                .findFirst();

        // if the product is present in the cart then update quantity
        // otherwise add new item
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + request.getQuantity();
            if (newQuantity > productResponse.getResult().getQuantity()) {
                throw new RuntimeException("Quantity exceeded");
            }
            item.setQuantity(newQuantity);
            cartItemRepository.save(item);

        } else {
            CartItem newItem = CartItem.builder()
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .cart(cart)
                    .build();

            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
        }
    }

    @Transactional
    public void updateItem(String token, String itemId, UpdateItemRequest request) {
        log.info("Cart service; Update item");

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getCart().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        var productResponse = productClient.getProduct(item.getProductId());
        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }

        if (request.getQuantity() > productResponse.getResult().getQuantity()) {
            throw new RuntimeException("Quantity exceeded");
        }

        item.setQuantity(request.getQuantity());
        cartItemRepository.save(item);
    }

    @Transactional
    public void deleteItem(String token, String itemId) {
        log.info("Cart service; Delete item");

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getCart().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        cartItemRepository.delete(item);
    }

    @Transactional
    public void emptyCart(String token) {
        log.info("Cart service; Empty cart");

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private AuthResponse validateToken(String token) {
        var authResponse = userClient.authenticate(token);
        if (authResponse.getResult() == null) {
            throw new RuntimeException("Token invalid");
        }

        return authResponse.getResult();
    }

}
