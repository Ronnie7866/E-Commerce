package com.backend.ecommerce.implementation;

import com.backend.ecommerce.entity.Inventory;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.exception.InsufficientStockException;
import com.backend.ecommerce.exception.InventoryNotFoundException;
import com.backend.ecommerce.exception.ProductNotFoundException;
import com.backend.ecommerce.exception.ResourceNotFoundException;
import com.backend.ecommerce.repository.InventoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryServiceImplementation implements InventoryService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImplementation(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public Inventory addInventory(Long productId, Integer stock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with this id:" + productId));
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(stock);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateStock(Long productId, Integer stock) {
        Inventory inventory = inventoryRepository.findByProduct(productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found")))
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
        inventory.setQuantity(stock);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getInventoryByProduct(Long productId) throws InventoryNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with this id: " + productId));
        Optional<Inventory> byProduct = inventoryRepository.findByProduct(product);
        if (byProduct.isEmpty()) {
            throw new InventoryNotFoundException("Inventory not found with this product");
        }
        return byProduct.get();
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory increaseStock(Long productId, Integer quantity) throws InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findByProduct(productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found with this id: " + productId)))
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory decreaseStock(Long productId, Integer quantity) throws InsufficientStockException, InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findByProduct(productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found with this id: " + productId)))
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        if (inventory.getQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock available");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Boolean isStockAvailable(Long productId, Integer quantity) throws InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findByProduct(productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found with this id: " + productId)))
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        return inventory.getQuantity() >= quantity;
    }

    @Override
    public void bulkUpdateStock(Map<Long, Integer> stockUpdates) throws InventoryNotFoundException {
        for (Map.Entry<Long, Integer> entry : stockUpdates.entrySet()) {
            Long productId = entry.getKey();
            Integer stock = entry.getValue();

            Inventory inventory = inventoryRepository.findByProduct(productRepository.findById(productId)
                            .orElseThrow(() -> new ProductNotFoundException("Product not found with this id: " + productId)))
                    .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

            inventory.setQuantity(stock);
            inventoryRepository.save(inventory);
        }
    }
}
