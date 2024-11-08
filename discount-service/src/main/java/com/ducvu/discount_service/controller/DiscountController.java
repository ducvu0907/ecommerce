package com.ducvu.discount_service.controller;

import com.ducvu.discount_service.dto.DiscountDto;
import com.ducvu.discount_service.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    // seller
    @PostMapping("")
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody DiscountDto discountDto) {

    }

    // user
    @GetMapping("/{discountId}")
    public ResponseEntity<DiscountDto> getDiscountById(@PathVariable String discountId) {

    }

    // user
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<DiscountDto>> getDiscountsByProductId(@PathVariable String productId) {

    }

    // admin
    public ResponseEntity<DiscountDto> updateDiscount(String discountId, DiscountDto discountDto) {

    }
    // admin
    @GetMapping("/{discountId}")
    public ResponseEntity<Boolean> deleteDiscount(@PathVariable String discountId) {

    }
}
