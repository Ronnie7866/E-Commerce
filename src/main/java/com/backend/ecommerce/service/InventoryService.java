package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Inventory;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.exception.InsufficientStockException;
import com.backend.ecommerce.exception.InventoryNotFoundException;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    Inventory addInventory(Long productId, Integer stock);
    Inventory updateStock(Long productId, Integer stock);
    Inventory getInventoryByProduct(Long productId) throws InventoryNotFoundException;
    List<Inventory> getAllInventory();
    Inventory increaseStock(Long productId, Integer quantity) throws InventoryNotFoundException;
    Inventory decreaseStock(Long productId, Integer quantity) throws InsufficientStockException, InventoryNotFoundException;
    Boolean isStockAvailable(Long productId, Integer quantity) throws InventoryNotFoundException;
    void bulkUpdateStock(Map<Long, Integer> stockUpdates) throws InventoryNotFoundException;
}
