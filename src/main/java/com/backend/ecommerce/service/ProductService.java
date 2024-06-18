package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> addAll(List<Product> products);

    List<Product> getAllProducts();

    Product getProductById(String id);

    Product updateProduct(String id, Product product);

    List<Product> getProductByCategory(Long catId);

    Product assignCategoryToProduct(String productId, Long categoryId);
}
