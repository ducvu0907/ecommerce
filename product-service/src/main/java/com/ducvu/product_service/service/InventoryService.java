package com.ducvu.product_service.service;

import com.ducvu.product_service.config.UserClient;
import com.ducvu.product_service.dto.request.InventoryCreateRequest;
import com.ducvu.product_service.dto.request.InventoryUpdateRequest;
import com.ducvu.product_service.dto.response.InventoryResponse;
import com.ducvu.product_service.entity.Inventory;
import com.ducvu.product_service.entity.Product;
import com.ducvu.product_service.helper.Mapper;
import com.ducvu.product_service.repository.InventoryRepository;
import com.ducvu.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final UserClient userClient;
    private final Mapper mapper;

    public List<InventoryResponse> getInventoriesByProductId(String token, String productId) {
        log.info("Inventory service; Get inventories by seller id");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getUserId().equals(product.getSellerId()) || !authResponse.getResult().getRole().equals("SELLER")) {
            throw new RuntimeException("Unauthorized");
        }

        return inventoryRepository.findByProductId(productId)
                .stream()
                .map(mapper::toInventoryResponse)
                .toList();
    }

    public InventoryResponse createInventory(String token, InventoryCreateRequest request) {
        log.info("Inventory service; Create inventory");

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getUserId().equals(product.getSellerId()) || !authResponse.getResult().getRole().equals("SELLER")) {
            throw new RuntimeException("Unauthorized");
        }

        Inventory inventory = Inventory.builder()
                .product(product)
                .location(request.getLocation())
                .stock(request.getStock())
                .build();

        return mapper.toInventoryResponse(inventoryRepository.save(inventory));
    }

    public InventoryResponse updateInventory(String token, String inventoryId, InventoryUpdateRequest request) {
        log.info("Inventory service; Update inventory");

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getUserId().equals(inventory.getProduct().getSellerId()) || !authResponse.getResult().getRole().equals("SELLER")) {
            throw new RuntimeException("Unauthorized");
        }

        if (request.getLocation() != null) {
            inventory.setLocation(request.getLocation());
        }

        if (request.getStock() != null) {
            inventory.setStock(request.getStock());
        }

        return mapper.toInventoryResponse(inventoryRepository.save(inventory));
    }

}
