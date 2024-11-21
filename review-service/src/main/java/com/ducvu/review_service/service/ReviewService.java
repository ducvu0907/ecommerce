package com.ducvu.review_service.service;

import com.ducvu.review_service.config.ProductClient;
import com.ducvu.review_service.config.UserClient;
import com.ducvu.review_service.dto.request.AuthRequest;
import com.ducvu.review_service.dto.request.CreateReviewRequest;
import com.ducvu.review_service.dto.request.UpdateReviewRequest;
import com.ducvu.review_service.dto.response.ReviewResponse;
import com.ducvu.review_service.entity.Review;
import com.ducvu.review_service.helper.Mapper;
import com.ducvu.review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ReviewResponse> getReviewsByProductId(Integer productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(mapper::toReviewResponse)
                .toList();
    }

    public ReviewResponse createReview(CreateReviewRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        reviewRepository.findByUserIdAndProductId(authResponse.getResult().getUserId(), request.getProductId())
                .ifPresent(review -> {
                    throw new RuntimeException("Review already exists");
                });

        var productResponse = productClient.getProduct(request.getProductId());

        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }

        if (productResponse.getResult().getSellerId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Can't review your own product");
        }

        Review review = Review.builder()
                .userId(authResponse.getResult().getUserId())
                .productId(request.getProductId())
                .rating(request.getRating())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
        return mapper.toReviewResponse(review);
    }

    public ReviewResponse updateReview(String reviewId, UpdateReviewRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUserId().equals(authResponse.getResult().getUserId())) {
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

    public void deleteReview(String reviewId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not exists"));

        if (!review.getUserId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        reviewRepository.delete(review);
    }

}
