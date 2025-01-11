package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.request.ShipmentRequest;
import com.ducvu.product_service.entity.Inventory;
import com.ducvu.product_service.entity.Shipment;
import com.ducvu.product_service.repository.InventoryRepository;
import com.ducvu.product_service.repository.ProductRepository;
import com.ducvu.product_service.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    // simple shipment planning (currently not considering geolocation)
    // need refactor later into a separate shipment service
    public void planOrderShipment(ShipmentRequest request) {
        String orderId = request.getOrderId();
        request.getProducts().forEach((item) -> {
            String productId = item.getProductId();
            Integer quantity = item.getQuantity();

            // prioritize inventory with higher stock
            List<Inventory> inventories = inventoryRepository.findByProductId(productId);
            inventories.sort(Comparator.comparingInt(Inventory::getStock).reversed());

            for (Inventory inventory : inventories) {
                if (quantity <= 0) break;
                int allocatedQuantity = Math.min(quantity, inventory.getStock());

                if (allocatedQuantity > 0) {
                    inventory.setStock(inventory.getStock() - allocatedQuantity);
                    inventoryRepository.save(inventory);

                    Shipment shipment = Shipment.builder()
                            .orderId(orderId)
                            .inventoryId(inventory.getId())
                            .quantity(allocatedQuantity)
                            .build();

                    shipmentRepository.save(shipment);
                    quantity -= allocatedQuantity;
                }
            }
            if (quantity > 0) {
                throw new RuntimeException("Not enough stock");
            }
        });
    }

    public void cancelOrderShipment(String orderId) {
        List<Shipment> shipments = shipmentRepository.findByOrderId(orderId);

        shipments.forEach((shipment -> {
            Inventory inventory = inventoryRepository.findById(shipment.getInventoryId())
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));

            inventory.setStock(inventory.getStock() + shipment.getQuantity());
            inventoryRepository.save(inventory);
        }));
    }

}
