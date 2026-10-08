package com.yash.ecommerce_backend.category.controller;

import com.yash.ecommerce_backend.category.dto.CategoryRequest;
import com.yash.ecommerce_backend.category.dto.CategoryResponse;
import com.yash.ecommerce_backend.category.entity.Category;
import com.yash.ecommerce_backend.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(
            @Valid @RequestBody CategoryRequest request
            ){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category savedCategory = categoryService.createCategory(category);
        CategoryResponse response = new CategoryResponse();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());
        response.setDescription(savedCategory.getDescription());
        response.setCreatedAt(savedCategory.getCreatedAt());
        return response;
    }
    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return categories.stream()
                .map(category-> {
                    CategoryResponse response = new CategoryResponse();
                    response.setId(category.getId());
                    response.setName(category.getName());
                    response.setDescription(category.getDescription());
                    response.setCreatedAt(category.getCreatedAt());
                    return response;
                })
                .toList();
    }
    @GetMapping("/{id}")
    public CategoryResponse getcategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setCreatedAt(category.getCreatedAt());
        return response;
    }
@PutMapping("/{id}")
    public CategoryResponse updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category updatedCategory = categoryService.updateCategory(id,category);
        CategoryResponse response = new CategoryResponse();
        response.setId(updatedCategory.getId());
        response.setName(updatedCategory.getName());
        response.setDescription(updatedCategory.getDescription());
        response.setCreatedAt(updatedCategory.getCreatedAt());
        return response;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }



}
