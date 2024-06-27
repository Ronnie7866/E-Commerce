package com.backend.ecommerce.service;

import com.backend.ecommerce.records.ProductResponse;
import com.backend.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> addAll(List<Product> products);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    List<Product> getProductByCategory(Long catId);

    Product assignCategoryToProduct(Long productId, Long categoryId);

    Product deleteProduct(Long id);

    ProductResponse getAllProductWithPagination(Integer pageNo, Integer pageSize);

    ProductResponse getProductWithPaginationAndSorting(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);
}
