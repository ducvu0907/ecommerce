package com.ducvu.review_service.controller;

import com.ducvu.review_service.dto.ReviewDto;
import com.ducvu.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // user
    @PostMapping("")
    public ResponseEntity<ReviewDto> createReview(ReviewDto reviewDto) {

    }

    // public
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Integer productId) {

    }

    // public
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable String reviewId) {

    }

    // user
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable String reviewId, @RequestBody ReviewDto reviewDto) {

    }

    // user
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable String reviewId) {

    }
}
