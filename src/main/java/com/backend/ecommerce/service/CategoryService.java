package com.backend.ecommerce.service;


import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with this id"));
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
        existingCategory.setProducts(category.getProducts());
        return categoryRepository.save(existingCategory);
    }

    public List<Category> createListOfCategory(List<Category> categoryList) {
        return categoryRepository.saveAll(categoryList);
    }
}
