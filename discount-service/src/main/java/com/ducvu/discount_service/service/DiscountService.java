package com.ducvu.discount_service.service;

import com.ducvu.discount_service.dto.response.DiscountResponse;
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

    public List<DiscountResponse> getDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(mapper::toDiscountResponse)
                .toList();
    }

}
