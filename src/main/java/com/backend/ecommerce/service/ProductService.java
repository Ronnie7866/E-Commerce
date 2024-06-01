package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.entity.ProductCategory;
import com.backend.ecommerce.repository.CategoryRepository;
import com.backend.ecommerce.repository.ProductCategoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;


    public Product createProduct(Product product) {
        // Save product in MongoDB
        Product savedProduct = productRepository.save(product);

        // Save mappings in SQL
        for (Long categoryId : product.getCategoryIds()) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductId(savedProduct.getId());
            productCategory.setCategoryId(categoryId);
            productCategoryRepository.save(productCategory);
        }

        return savedProduct;
    }

    public List<Product> addAll(List<ProductDTO> productDTOS) {
        List<Product> productList = new ArrayList<>();
        for (ProductDTO productDTO : productDTOS) {
            Product product = createProduct(productDTO);
            productList.add(product);
        }
        return productRepository.saveAll(productList);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        // Get product from MongoDB
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            // Get category IDs from the junction table
            List<Long> categoryIds = productCategoryRepository.findCategoryIdsByProductId(id);
            product.setCategoryIds(categoryIds);
        }

        return product;
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setId(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.deleteById(id);
        return product;
    }

    public List<Product> getProductByCategory(Category category) {
        return new ArrayList<>(category.getProducts());
    }

    public Product assignCategoryToProduct(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.getCategories().add(category);
        return productRepository.save(product);
    }
}
