package com.backend.ecommerce.service;


import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.repository.CategoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with this id"));
    }

    public List<Category> getCategoryByProductId(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with this id"));
        List<Category> categoryList = new ArrayList<>();
        for (var i : product.getCategoryIds()) {
            Category cat = categoryRepository.findById(i).get();
            categoryList.add(cat);
        }
        return categoryList;
    }
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public String delete(Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted successfully";
    }

    public Category update(Category category, Long id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id"));
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setProductIds(category.getProductIds());
        return categoryRepository.save(existingCategory);
    }

    public List<Category> createListOfCategory(List<Category> categoryList) {
        return categoryRepository.saveAll(categoryList);
    }
}
