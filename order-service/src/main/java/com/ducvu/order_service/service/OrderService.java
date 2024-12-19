package com.ducvu.order_service.service;

import com.ducvu.order_service.config.*;
import com.ducvu.order_service.dto.request.*;
import com.ducvu.order_service.dto.response.OrderItemResponse;
import com.ducvu.order_service.dto.response.OrderResponse;
import com.ducvu.order_service.entity.Order;
import com.ducvu.order_service.entity.OrderItem;
import com.ducvu.order_service.helper.Mapper;
import com.ducvu.order_service.repository.OrderItemRepository;
import com.ducvu.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Mapper mapper;
    private final UserClient userClient;
    private final CartClient cartClient;
    private final DiscountClient discountClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;

    public List<OrderResponse> getMyOrders(AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        List<Order> orders = orderRepository.findByUserId(authResponse.getResult().getUserId());
        return orders.stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse getOrder(Integer orderId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        return mapper.toOrderResponse(order);
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        // authenticate user first
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        // get user cart
        var cartResponse = cartClient.getMyCart(authRequest);
        if (cartResponse == null) {
            throw new RuntimeException("Cart is invalid");
        }

        Order order = new Order();
        order.setItems(new HashSet<>());
        order.setUserId(authResponse.getResult().getUserId());

        if (request.getDescription() != null) {
            order.setDescription(request.getDescription());
        }

        if (request.getItems().isEmpty()) {
            throw new RuntimeException("Order is empty");
        }

        // loop over each item in the cart to recheck
        double totalAmount = 0.0;
        List<UpdateProductRequest> productsUpdate = new ArrayList<>(); // for sending products update request
        List<CartItemRequest> cartItemRequests = new ArrayList<>(); // for sending request to update items in the cart

        for (var item : request.getItems()) {
            var productResponse = productClient.getProduct(item.getProductId());
            if (productResponse == null) {
                throw new RuntimeException("Product not found");
            }
            if (item.getQuantity() > productResponse.getResult().getQuantity()) {
                throw new RuntimeException("Item quantity exceeds product quantity");
            }
            totalAmount += item.getPrice();
            OrderItem newItem = mapper.toOrderItem(item);
            newItem.setOrder(order);
            orderItemRepository.save(newItem);
            order.getItems().add(newItem);
            var product = UpdateProductRequest.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build();
            productsUpdate.add(product);
            var cartItem = CartItemRequest.builder()
                    .price(item.getPrice())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build();
            cartItemRequests.add(cartItem);
        }

        order.setTotalAmount(totalAmount);

        // validate the discount id if present
        if (request.getDiscountId() != null) {
            var discountResponse = discountClient.getDiscount(request.getDiscountId());
            if (discountResponse == null) {
                throw new RuntimeException("Discount invalid");
            }
            order.setDiscountId(request.getDiscountId());
            if (discountResponse.getResult().getAmount() != null) {
                order.setTotalAmount(order.getTotalAmount() - discountResponse.getResult().getAmount());
            } else if (discountResponse.getResult().getPercent() != null) {
                order.setTotalAmount(order.getTotalAmount() * (1 - discountResponse.getResult().getPercent() / 100));
            }
        }

        order.setStatus("pending"); // default status
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);

        // update cart
        var cartUpdateRequest = UpdateCartRequest.builder()
                .token(request.getToken())
                .items(cartItemRequests)
                .build();
        cartClient.order(cartUpdateRequest);

        // update products
        var productUpdateRequest = OrderProductsRequest.builder()
                .isOrderCanceled(false)
                .products(productsUpdate)
                .build();
        productClient.order(productUpdateRequest);

        // create payment
        CreatePaymentRequest paymentRequest = CreatePaymentRequest.builder().token(request.getToken()).orderId(order.getId()).build();
        paymentClient.createPayment(paymentRequest);

        return mapper.toOrderResponse(order);
    }

    public void cancelOrder(Integer orderId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // send request to revert product quantity
        var productUpdateRequest = OrderProductsRequest.builder()
                .isOrderCanceled(true)
                .products(new ArrayList<>())
                .build();
        for (var item : order.getItems()) {
            var productUpdateItem = UpdateProductRequest.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build();
            productUpdateRequest.getProducts().add(productUpdateItem);
        }
        productClient.order(productUpdateRequest);

        if (!order.getUserId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        paymentClient.deletePayment(orderId, request);
        orderRepository.delete(order);
    }

}
