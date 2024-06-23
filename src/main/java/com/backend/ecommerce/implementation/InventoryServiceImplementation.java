package com.backend.ecommerce.implementation;

import com.backend.ecommerce.entity.Inventory;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.exception.ResourceNotFoundException;
import com.backend.ecommerce.repository.InventoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryServiceImplementation implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public Inventory getInventoryByProduct(Long productId) {
        return inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void updateInventory(Long productId, Integer quantity) {
        Product oldProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Product product = Product.builder()
                .stockQuantity(quantity)
                .build();
        productRepository.save(product);
    }

    @Override
    public boolean isProductAvailable(Long productId) {
        return false;
    }
}
