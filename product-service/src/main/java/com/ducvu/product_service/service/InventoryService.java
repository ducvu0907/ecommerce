package com.ducvu.product_service.service;

import com.ducvu.product_service.config.UserClient;
import com.ducvu.product_service.dto.request.InventoryCreateRequest;
import com.ducvu.product_service.dto.request.InventoryUpdateRequest;
import com.ducvu.product_service.dto.response.AuthResponse;
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

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        if (!userId.equals(product.getSellerId())) {
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

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        if (!userId.equals(product.getSellerId())) {
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

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();
        String role = authResponse.getRole();

        if (!userId.equals(inventory.getProduct().getSellerId())) {
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

    public InventoryResponse deleteInventory(String token, String inventoryId) {
        log.info("Inventory service; Delete inventory");

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        if (!userId.equals(inventory.getProduct().getSellerId())) {
            throw new RuntimeException("Unauthorized");
        }

        inventoryRepository.delete(inventory);
        return mapper.toInventoryResponse(inventory);
    }

    private AuthResponse validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("No token provided");
        }
        var authResponse = userClient.authenticate(token);
        if (authResponse.getResult() == null) {
            throw new RuntimeException("Token invalid");
        }
        return authResponse.getResult();
    }
}
