package com.yash.ecommerce_backend.category.service;

import com.yash.ecommerce_backend.category.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category updateCategory(Long id , Category category);
    void deleteCategory(Long id);
}
