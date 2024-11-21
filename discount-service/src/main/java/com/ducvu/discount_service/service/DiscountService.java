package com.ducvu.discount_service.service;

import com.ducvu.discount_service.config.UserClient;
import com.ducvu.discount_service.dto.request.AuthRequest;
import com.ducvu.discount_service.dto.request.CreateDiscountRequest;
import com.ducvu.discount_service.dto.request.UpdateDiscountRequest;
import com.ducvu.discount_service.dto.response.DiscountResponse;
import com.ducvu.discount_service.entity.Discount;
import com.ducvu.discount_service.helper.Mapper;
import com.ducvu.discount_service.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final Mapper mapper;
    private final UserClient userClient;

    public List<DiscountResponse> getDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(mapper::toDiscountResponse)
                .toList();
    }

    public DiscountResponse getDiscount(String discountId) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        return mapper.toDiscountResponse(discount);
    }

    public DiscountResponse createDiscount(CreateDiscountRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        if (!authResponse.getResult().getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }

        Discount discount = Discount.builder()
                .description(request.getDescription())
                .amount(request.getAmount())
                .percent(request.getPercent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isActive(request.getIsActive())
                .build();

        discountRepository.save(discount);
        return mapper.toDiscountResponse(discount);
    }

    public DiscountResponse updateDiscount(String discountId, UpdateDiscountRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        if (!authResponse.getResult().getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }

        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("Discount not found"));

        if (request.getDescription() != null) {
            discount.setDescription(request.getDescription());
        }

        if (request.getAmount() != null) {
            discount.setAmount(request.getAmount());
        }
        if (request.getPercent() != null) {
            discount.setPercent(request.getPercent());
        }
        if (request.getStartDate() != null) {
            discount.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            discount.setEndDate(request.getEndDate());
        }
        if (request.getIsActive() != null) {
            discount.setIsActive(request.getIsActive());
        }

        discountRepository.save(discount);
        return mapper.toDiscountResponse(discount);
    }

    public void deleteDiscount(String discountId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        if (!authResponse.getResult().getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }

        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("Discount not found"));

        discountRepository.delete(discount);
    }
}
