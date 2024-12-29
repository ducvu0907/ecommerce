package com.ducvu.review_service.service;

import com.ducvu.review_service.config.ProductClient;
import com.ducvu.review_service.config.UserClient;
import com.ducvu.review_service.dto.request.CreateReviewRequest;
import com.ducvu.review_service.dto.request.UpdateReviewRequest;
import com.ducvu.review_service.dto.response.AuthResponse;
import com.ducvu.review_service.dto.response.ReviewResponse;
import com.ducvu.review_service.entity.Review;
import com.ducvu.review_service.helper.Mapper;
import com.ducvu.review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final Mapper mapper;
    private final UserClient userClient;
    private final ProductClient productClient;

    public List<ReviewResponse> getReviewsByProductId(String productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(mapper::toReviewResponse)
                .toList();
    }

    public ReviewResponse createReview(String token, CreateReviewRequest request) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();
        String role = authResponse.getRole();

        if (role.equals("SELLER")) {
            throw new RuntimeException("Seller can't review products");
        }

        reviewRepository.findByUserIdAndProductId(userId, request.getProductId())
                .ifPresent(review -> {
                    throw new RuntimeException("Review already exists");
                });

        var productResponse = productClient.getProduct(request.getProductId());
        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }

        Review review = Review.builder()
                .userId(userId)
                .productId(request.getProductId())
                .rating(request.getRating())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
        return mapper.toReviewResponse(review);
    }

    public ReviewResponse updateReview(String token, String reviewId, UpdateReviewRequest request) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        if (request.getRating() != null) {
            review.setRating(request.getRating());
        }

        if (request.getContent() != null) {
            review.setContent(request.getContent());
        }

        review.setUpdatedAt(LocalDateTime.now());
        reviewRepository.save(review);
        return mapper.toReviewResponse(review);
    }

    public void deleteReview(String token, String reviewId) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not exists"));

        if (!review.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        reviewRepository.delete(review);
    }

    private AuthResponse validateToken(String token) {
        var authResponse = userClient.authenticate(token);
        if (authResponse.getResult() == null) {
            throw new RuntimeException("Token invalid");
        }
        return authResponse.getResult();
    }
}
