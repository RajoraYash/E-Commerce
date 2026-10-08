package com.yash.ecommerce_backend.category.service.impl;

import com.yash.ecommerce_backend.category.entity.Category;
import com.yash.ecommerce_backend.category.repository.CategoryRepository;
import com.yash.ecommerce_backend.category.service.CategoryService;
import org.springframework.stereotype.Service;
import com.yash.ecommerce_backend.common.exception.CategoryNotFoundException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found with id: " + id));
    }
    @Override
    public Category updateCategory(Long id , Category category){
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category Not Found with id: " + id));
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }
    @Override
    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);

    }


}
