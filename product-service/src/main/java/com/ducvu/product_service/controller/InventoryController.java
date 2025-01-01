package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.ApiResponse;
import com.ducvu.product_service.dto.request.InventoryCreateRequest;
import com.ducvu.product_service.dto.request.InventoryUpdateRequest;
import com.ducvu.product_service.dto.response.InventoryResponse;
import com.ducvu.product_service.repository.InventoryRepository;
import com.ducvu.product_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@Slf4j
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/product/{productId}")
    public ApiResponse<List<InventoryResponse>> getInventoriesByProductId(@RequestHeader("token") String token, @PathVariable("productId") String productId) {
        var res = inventoryService.getInventoriesByProductId(token, productId);
        return ApiResponse.<List<InventoryResponse>>builder().result(res).build();
    }

    @PostMapping("")
    public ApiResponse<InventoryResponse> createInventory(@RequestHeader("token") String token, InventoryCreateRequest request) {
        var res = inventoryService.createInventory(token, request);
        return ApiResponse.<InventoryResponse>builder().result(res).build();
    }

    @PostMapping("/{inventoryId}")
    public ApiResponse<InventoryResponse> updateInventory(@RequestHeader("token") String token, @PathVariable("inventoryId") String inventoryId, InventoryUpdateRequest request) {
        var res = inventoryService.updateInventory(token, inventoryId, request);
        return ApiResponse.<InventoryResponse>builder().result(res).build();
    }

}
