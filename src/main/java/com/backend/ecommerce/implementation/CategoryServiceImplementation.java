package com.backend.ecommerce.implementation;


import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.repository.CategoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImplementation implements com.backend.ecommerce.service.CategoryService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with this id"));
    }

    @Override
    public List<Category> getCategoryByProductId(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with this id"));
        List<Category> categoryList = new ArrayList<>();
        for (var i : product.getCategoryIds()) {
            Category cat = categoryRepository.findById(i).get();
            categoryList.add(cat);
        }
        return categoryList;
    }
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public String delete(Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted successfully";
    }

    @Override
    public Category update(Category category, Long id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id"));
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setProductIds(category.getProductIds());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public List<Category> createListOfCategory(List<Category> categoryList) {
        return categoryRepository.saveAll(categoryList);
    }
}
