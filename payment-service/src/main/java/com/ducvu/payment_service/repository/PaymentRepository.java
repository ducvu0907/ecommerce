package com.ducvu.payment_service.repository;

import com.ducvu.payment_service.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByUserId(Integer userId);
}
