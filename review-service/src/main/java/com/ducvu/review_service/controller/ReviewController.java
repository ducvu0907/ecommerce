package com.ducvu.review_service.controller;

import com.ducvu.review_service.dto.request.AuthRequest;
import com.ducvu.review_service.dto.request.CreateReviewRequest;
import com.ducvu.review_service.dto.request.UpdateReviewRequest;
import com.ducvu.review_service.dto.response.ApiResponse;
import com.ducvu.review_service.dto.response.ReviewResponse;
import com.ducvu.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // public
    @GetMapping("/product/{productId}")
    public ApiResponse<List<ReviewResponse>> getReviewsByProductId(@PathVariable("productId") Integer productId) {
        var res = reviewService.getReviewsByProductId(productId);
        return ApiResponse.<List<ReviewResponse>>builder().result(res).build();
    }


    // buyer
    @PostMapping("")
    public ApiResponse<ReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
        var res = reviewService.createReview(request);
        return ApiResponse.<ReviewResponse>builder().result(res).build();
    }

    // buyer
    @PostMapping("/{reviewId}")
    public ApiResponse<ReviewResponse> updateReview(@PathVariable("reviewId") String reviewId, @RequestBody UpdateReviewRequest request) {
        var res = reviewService.updateReview(reviewId, request);
        return ApiResponse.<ReviewResponse>builder().result(res).build();
    }

    // buyer
    @DeleteMapping("/{reviewId}")
    public ApiResponse<String> deleteReview(@PathVariable("reviewId") String reviewId, @RequestBody AuthRequest request) {
        reviewService.deleteReview(reviewId, request);
        return ApiResponse.<String>builder().result("Review has been deleted").build();
    }

}
