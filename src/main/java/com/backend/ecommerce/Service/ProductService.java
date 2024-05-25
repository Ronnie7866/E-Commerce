package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.Category;
import com.backend.ecommerce.Entity.Product;
import com.backend.ecommerce.Repository.CartRepository;
import com.backend.ecommerce.Repository.CategoryRepository;
import com.backend.ecommerce.Repository.ProductRepository;
import com.backend.ecommerce.dto.AddProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public Product createProduct(AddProductDto apd) {
//        List<Long> categoryListIds = product.getCategories().stream().map(Category::getId).toList();
        List<Category> categoryList = categoryRepository.findAllById(apd.categoryIds());
        Product newProduct = Product.builder()
                .name(apd.name())
                .price(apd.price())
                .description(apd.description())
                .categories(categoryList)
                .build();
//        product.setCategories(categoryList);
        return productRepository.save(newProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
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
