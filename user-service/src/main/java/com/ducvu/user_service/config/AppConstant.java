package com.ducvu.user_service.config;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AppConstant {
    public static final String USER_SERVICE_HOST = "http://user-service/user-service";
    public static final String USER_SERVICE_API_URL = "http://user-service/user-service/api/users";

    public static final String CART_SERVICE_HOST = "http://cart-service/cart-service";
    public static final String CART_SERVICE_API_URL = "http://cart-service/cart-service/api/carts";

    public static final String ORDER_SERVICE_HOST = "http://order-service/order-service";
    public static final String ORDER_SERVICE_API_URL = "http://order-service/order-service/api/orders";

    public static final String REVIEW_SERVICE_HOST = "http://review-service/review-service";
    public static final String REVIEW_SERVICE_API_URL = "http://review-service/review-service/api/reviews";

    public static final String PAYMENT_SERVICE_HOST = "http://payment-service/payment-service";
    public static final String PAYMENT_SERVICE_API_URL = "http://payment-service/payment-service/api/payments";

    public static final String PRODUCT_SERVICE_HOST = "http://product-service/product-service";
    public static final String PRODUCT_SERVICE_API_URL = "http://product-service/product-service/api/products";

    public static final String DISCOUNT_SERVICE_HOST = "http://discount-service/discount-service";
    public static final String DISCOUNT_SERVICE_API_URL = "http://discount-service/discount-service/api/discounts";
}
