package com.backend.ecommerce.service;

public interface InventoryService {

    void updateInventory(Long productId, Integer quantity);
    boolean isProductAvailable(Long productId);
}
