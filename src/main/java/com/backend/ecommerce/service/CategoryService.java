package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    List<Category> getCategoryByProductId(Long productId);

    Category create(Category category);

    String delete(Long id);

    Category update(Category category, Long id);

    List<Category> createListOfCategory(List<Category> categoryList);
}
