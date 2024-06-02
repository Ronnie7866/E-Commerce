package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.exception.ResourceNotFoundException;
import com.backend.ecommerce.repository.CategoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addAll(List<Product> products) {
        List<Product> productList = new ArrayList<>();
        for (Product i : products) {
            Product product = createProduct(i);
            productList.add(product);
        }
        return productRepository.saveAll(productList);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setId(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    public List<Product> getProductByCategory(Long catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found with this Id : " + catId));
        List<Product> productList = new ArrayList<>();
        for (var i : category.getProductIds()) {
            Product product = productRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("Product not found with this id : " + i));
            productList.add(product);
        }
        return productList;
    }

    public Product assignCategoryToProduct(String productId, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Long id = category.getId();
        product.getCategoryIds().add(id);
        return productRepository.save(product);
    }
}
