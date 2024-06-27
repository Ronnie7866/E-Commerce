package com.backend.ecommerce.controllers;

import com.backend.ecommerce.entity.Inventory;
import com.backend.ecommerce.exception.InsufficientStockException;
import com.backend.ecommerce.exception.InventoryNotFoundException;
import com.backend.ecommerce.implementation.InventoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryServiceImplementation inventoryServiceImplementation;

    @Autowired
    public InventoryController(InventoryServiceImplementation inventoryServiceImplementation) {
        this.inventoryServiceImplementation = inventoryServiceImplementation;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Inventory> addInventory(@PathVariable Long productId, @RequestParam int stock) {
        Inventory inventory = inventoryServiceImplementation.addInventory(productId, stock);
        return new ResponseEntity<>(inventory, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Inventory> updateStock(@PathVariable Long productId, @RequestParam int stock) {
        Inventory inventory = inventoryServiceImplementation.updateStock(productId, stock);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventoryByProduct(@PathVariable Long productId) {
        Inventory inventory = null;
        try {
            inventory = inventoryServiceImplementation.getInventoryByProduct(productId);
        } catch (InventoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryServiceImplementation.getAllInventory();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @PostMapping("/decrease/{productId}")
    public ResponseEntity<Inventory> decreaseStock(@PathVariable Long productId, @RequestParam int quantity) {
        Inventory inventory = null;
        try {
            inventory = inventoryServiceImplementation.decreaseStock(productId, quantity);
        } catch (InsufficientStockException | InventoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/increase/{productId}")
    public ResponseEntity<Inventory> increaseStock(@PathVariable Long productId, @RequestParam int quantity) {
        Inventory inventory = null;
        try {
            inventory = inventoryServiceImplementation.increaseStock(productId, quantity);
        } catch (InventoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/check/{productId}")
    public ResponseEntity<Boolean> isStockAvailable(@PathVariable Long productId, @RequestParam int quantity) {
        boolean isAvailable = false;
        try {
            isAvailable = inventoryServiceImplementation.isStockAvailable(productId, quantity);
        } catch (InventoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @PostMapping("/bulk-update")
    public ResponseEntity<Void> bulkUpdateStock(@RequestBody Map<Long, Integer> stockUpdates) {
        try {
            inventoryServiceImplementation.bulkUpdateStock(stockUpdates);
        } catch (InventoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
