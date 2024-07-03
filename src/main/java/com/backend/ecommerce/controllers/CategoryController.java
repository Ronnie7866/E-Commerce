package com.backend.ecommerce.controllers;


import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = categoryService.create(category);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.findAll();
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category byId = categoryService.findById(id);
        if (byId != null) {
            return ResponseEntity.ok(byId);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        Category updatedCategory = categoryService.update(category, id);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        String delete = categoryService.delete(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Category>> createCategory(@RequestBody List<Category> category) {
        List<Category> listOfCategory = categoryService.createListOfCategory(category);
        return ResponseEntity.ok(listOfCategory);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> findListOfCategoryByProductId(@RequestParam Long productId) {
        List<Category> categoryByProductId = categoryService.getCategoryByProductId(productId);
        return ResponseEntity.ok(categoryByProductId);
    }
}
