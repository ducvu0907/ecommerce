package com.ducvu.discount_service.service;

import com.ducvu.discount_service.dto.DiscountDto;
import com.ducvu.discount_service.repository.DiscountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DiscountService {
    private final DiscountRepository discountRepository;

    public ResponseEntity<DiscountDto> createDiscount(DiscountDto discountDto) {

    }

    public ResponseEntity<DiscountDto> getDiscountById(String discountId) {

    }

    public ResponseEntity<List<DiscountDto>> getDiscountsByProductId(String productId) {

    }

    public ResponseEntity<DiscountDto> updateDiscount(String discountId, DiscountDto discountDto) {

    }

    public ResponseEntity<Boolean> deleteDiscount(String discountId) {

    }

}
