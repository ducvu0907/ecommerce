package com.ducvu.discount_service.repository;

import com.ducvu.discount_service.entity.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiscountRepository extends MongoRepository<Discount, String> {
    List<Discount> findByProductId(Integer productId);
    Discount findByPromoCode(String promoCode);
}
