package com.ducvu.discount_service.repository;

import com.ducvu.discount_service.entity.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends MongoRepository<Discount, String> {
}
