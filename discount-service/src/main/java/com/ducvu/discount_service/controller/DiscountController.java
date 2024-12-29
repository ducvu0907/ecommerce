package com.ducvu.discount_service.controller;

import com.ducvu.discount_service.dto.ApiResponse;
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

    @GetMapping("{discountId}")
    public ApiResponse<DiscountResponse> getDiscount(@PathVariable("discountId") String discountId) {
        var res = discountService.getDiscount(discountId);
        return ApiResponse.<DiscountResponse>builder().result(res).build();
    }
}
