package com.ducvu.discount_service.controller;

import com.ducvu.discount_service.dto.request.AuthRequest;
import com.ducvu.discount_service.dto.request.CreateDiscountRequest;
import com.ducvu.discount_service.dto.request.UpdateDiscountRequest;
import com.ducvu.discount_service.dto.response.ApiResponse;
import com.ducvu.discount_service.dto.response.DiscountResponse;
import com.ducvu.discount_service.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("")
    public ApiResponse<List<DiscountResponse>> getDiscounts() {
        var res = discountService.getDiscounts();
        return ApiResponse.<List<DiscountResponse>>builder().result(res).build();
    }

    @GetMapping("/{discountId}")
    public ApiResponse<DiscountResponse> getDiscount(@PathVariable String discountId) {
        var res = discountService.getDiscount(discountId);
        return ApiResponse.<DiscountResponse>builder().result(res).build();
    }

    @PostMapping("")
    public ApiResponse<DiscountResponse> createDiscount(@RequestBody CreateDiscountRequest request) {
        var res = discountService.createDiscount(request);
        return ApiResponse.<DiscountResponse>builder().result(res).build();
    }

    @PostMapping("/{discountId}")
    public ApiResponse<DiscountResponse> updateDiscount(@PathVariable String discountId, @RequestBody UpdateDiscountRequest request) {
        var res = discountService.updateDiscount(discountId, request);
        return ApiResponse.<DiscountResponse>builder().result(res).build();
    }

    @DeleteMapping("/{discountId}")
    public ApiResponse<String> deleteDiscount(@PathVariable String discountId, @RequestBody AuthRequest request) {
        discountService.deleteDiscount(discountId, request);
        return ApiResponse.<String>builder().result("Discount has been deleted").build();
    }

}
