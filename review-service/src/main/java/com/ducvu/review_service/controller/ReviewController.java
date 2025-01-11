package com.ducvu.review_service.controller;

import com.ducvu.review_service.dto.request.CreateReviewRequest;
import com.ducvu.review_service.dto.request.UpdateReviewRequest;
import com.ducvu.review_service.dto.ApiResponse;
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

    @GetMapping("/product/{productId}")
    public ApiResponse<List<ReviewResponse>> getReviewsByProductId(@PathVariable("productId") String productId) {
        var res = reviewService.getReviewsByProductId(productId);
        return ApiResponse.<List<ReviewResponse>>builder().result(res).build();
    }


    @PostMapping("")
    public ApiResponse<ReviewResponse> createReview(@RequestHeader("token") String token, @RequestBody CreateReviewRequest request) {
        var res = reviewService.createReview(token, request);
        return ApiResponse.<ReviewResponse>builder().result(res).build();
    }

    @PostMapping("/{reviewId}")
    public ApiResponse<ReviewResponse> updateReview(@RequestHeader("token") String token, @PathVariable("reviewId") String reviewId, @RequestBody UpdateReviewRequest request) {
        var res = reviewService.updateReview(token, reviewId, request);
        return ApiResponse.<ReviewResponse>builder().result(res).build();
    }

    @DeleteMapping("/{reviewId}")
    public ApiResponse<String> deleteReview(@RequestHeader("token") String token, @PathVariable("reviewId") String reviewId) {
        reviewService.deleteReview(token, reviewId);
        return ApiResponse.<String>builder().result("Review has been deleted").build();
    }

}
