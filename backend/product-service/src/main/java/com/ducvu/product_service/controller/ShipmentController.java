package com.ducvu.product_service.controller;


import com.ducvu.product_service.dto.ApiResponse;
import com.ducvu.product_service.dto.request.ShipmentRequest;
import com.ducvu.product_service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
@Slf4j
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @PostMapping("/plan")
    public ApiResponse<String> planOrderShipment(@RequestBody ShipmentRequest request) {
        shipmentService.planOrderShipment(request);
        return ApiResponse.<String>builder().result("Plan order shipment successfully").build();
    }

    @DeleteMapping("/{orderId}/cancel")
    public ApiResponse<String> cancelOrderShipment(@PathVariable("orderId") String orderId) {
        shipmentService.cancelOrderShipment(orderId);
        return ApiResponse.<String>builder().result("Cancel order shipment successfully").build();
    }
}
