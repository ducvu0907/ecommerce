package com.ducvu.cart_service.service;

import com.ducvu.cart_service.config.ProductClient;
import com.ducvu.cart_service.config.UserClient;
import com.ducvu.cart_service.dto.request.AddItemRequest;
import com.ducvu.cart_service.dto.request.AuthRequest;
import com.ducvu.cart_service.dto.request.UpdateItemRequest;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final Mapper mapper;
    private final UserClient userClient;
    private final ProductClient productClient;

    public CartResponse getMyCart(AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Unauthorized");
        }
        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(authResponse.getResult().getUserId());
                    newCart.setItems(new HashSet<>());
                    return cartRepository.save(newCart);
                });

        return mapper.toCartResponse(cart);
    }

    @Transactional
    public CartResponse addItem(AddItemRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Unauthorized");
        }
        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(authResponse.getResult().getUserId());
                    newCart.setItems(new HashSet<>());
                    return cartRepository.save(newCart);
                });

        var productResponse = productClient.getProduct(request.getProductId());
        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }

        if (request.getQuantity() > productResponse.getResult().getQuantity()) {
            throw new RuntimeException("Quantity exceeded");
        }

        CartItem item = CartItem.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(productResponse.getResult().getPrice() * request.getQuantity())
                .cart(cart)
                .build();

        cartItemRepository.save(item);

        cart.getItems().add(item);
        cartRepository.save(cart);

        return mapper.toCartResponse(cart);
    }

    @Transactional
    public CartResponse updateItem(Integer itemId, UpdateItemRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Unauthorized");
        }

        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Item not found");
        }

        var productResponse = productClient.getProduct(item.getProductId());
        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }

        if (request.getQuantity() > productResponse.getResult().getQuantity()) {
            throw new RuntimeException("Quantity exceeded");
        }

        item.setQuantity(request.getQuantity());
        item.setPrice(productResponse.getResult().getPrice() * request.getQuantity());

        cartItemRepository.save(item);
        cartRepository.save(cart);

        return mapper.toCartResponse(cart);
    }

    public void deleteItem(Integer itemId, AuthRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Unauthorized");
        }

        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepository.delete(item);

        cart.getItems().remove(item);
        cartRepository.save(cart);
    }

}
