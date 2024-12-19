package com.ducvu.discount_service.helper;

import com.ducvu.discount_service.entity.Discount;
import com.ducvu.discount_service.repository.DiscountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataLoader {
    private final DiscountRepository discountRepository;
    private final ObjectMapper objectMapper;

    // initialize data
    @PostConstruct
    public void loadMockData() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/data.json");
            List<Discount> discounts = objectMapper.readValue(inputStream, new TypeReference<List<Discount>>() {});
            discountRepository.saveAll(discounts);
            log.info("Load initial discounts data: {}", discounts);

        } catch(Exception e) {
            log.error("Failed to load discounts: {}", e.getMessage());
        }
    }
}
