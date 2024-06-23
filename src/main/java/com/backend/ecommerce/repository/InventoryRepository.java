package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
