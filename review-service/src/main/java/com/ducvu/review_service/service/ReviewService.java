package com.ducvu.review_service.service;

import com.ducvu.review_service.dto.ReviewDto;
import com.ducvu.review_service.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewDto createReview(ReviewDto reviewDto) {

    }

    public List<ReviewDto> getReviewsByProductId(Integer productId) {

    }

    public ReviewDto getReviewById(String reviewId) {

    }

    public ReviewDto updateReview(String reviewId, ReviewDto reviewDto) {

    }

    public ReviewDto deleteReview(String reviewId) {

    }
}
