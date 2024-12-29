package com.ducvu.review_service.repository;

import com.ducvu.review_service.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByProductId(String productId);
    Optional<Review> findByUserIdAndProductId(String userId, String productId);
}
