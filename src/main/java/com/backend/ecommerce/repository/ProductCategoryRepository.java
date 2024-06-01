package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.ProductCategory;
import com.backend.ecommerce.entity.ProductCategoryId;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryId> {
    List<Long> findCategoryIdsByProductId(Long id);
}
