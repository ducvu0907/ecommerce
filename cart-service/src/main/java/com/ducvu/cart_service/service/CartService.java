package com.ducvu.cart_service.service;

import com.ducvu.cart_service.config.ProductClient;
import com.ducvu.cart_service.config.UserClient;
import com.ducvu.cart_service.dto.request.*;
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
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new RuntimeException("Token invalid");
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
            throw new RuntimeException("Token invalid");
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
            item.setPrice(productResponse.getResult().getPrice() * newQuantity);

            cartItemRepository.save(item);
        } else {
            CartItem newItem = CartItem.builder()
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .price(productResponse.getResult().getPrice() * request.getQuantity())
                    .cart(cart)
                    .build();

            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }

    @Transactional
    public CartResponse updateItem(Integer itemId, UpdateItemRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
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

    @Transactional
    public void deleteItem(Integer itemId, AuthRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepository.delete(item);

        cart.getItems().remove(item);
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteItems(AuthRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseThrow(() ->  new RuntimeException("Cart not found"));

        cartItemRepository.deleteAllInBatch(cart.getItems());

        //cart.getItems().forEach(item -> {
        //    cartItemRepository.delete(item);
        //});

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    // used by order service after successful order
    @Transactional
    public void updateCart(UpdateCartRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Cart cart = cartRepository.findByUserId(authResponse.getResult().getUserId())
                .orElseThrow(() ->  new RuntimeException("Cart not found"));

        // replace previous cart items with new set of items
        cart.getItems().clear();

        for (CartItemRequest itemRequest : request.getItems()) {
            CartItem newItem = CartItem.builder()
                    .productId(itemRequest.getProductId())
                    .quantity(itemRequest.getQuantity())
                    .price(itemRequest.getPrice())
                    .cart(cart)
                    .build();
            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
    }
}
