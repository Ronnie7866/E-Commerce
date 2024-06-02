package com.backend.ecommerce.controllers;


import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.service.CategoryService;
import com.backend.ecommerce.service.ProductService;
import com.backend.ecommerce.dto.ProductDTO;
import com.backend.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PostMapping("/add-all")
    public ResponseEntity<List<Product>> createListProduct(@RequestBody List<Product> productList) {
        List<Product> products = productService.addAll(productList);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product productById = productService.getProductById(id);
        return ResponseEntity.ok(productById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable String id) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("arib")
    public void aribfunction(){
        System.out.println("AribFunction");
    }

    @PostMapping("/mapCategory")
    public ResponseEntity<Product> assingProductWithCategory(@RequestParam String productId, @RequestParam Long categoryId) {
        Product product = productService.assignCategoryToProduct(productId, categoryId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getProductByCategory/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long id) {
        List<Product> productByCategory = productService.getProductByCategory(id);
        return ResponseEntity.ok(productByCategory);
    }
}
