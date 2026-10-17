package com.yash.ecommerce_backend.product.controller;

import com.yash.ecommerce_backend.category.entity.Category;
import com.yash.ecommerce_backend.product.dto.ProductRequest;
import com.yash.ecommerce_backend.product.dto.ProductResponse;
import com.yash.ecommerce_backend.product.entity.Product;
import com.yash.ecommerce_backend.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request
            ){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        com.yash.ecommerce_backend.category.entity.Category category =
                new com.yash.ecommerce_backend.category.entity.Category();
        category.setId(request.getCategoryId());
        product.setCategory(category);
        Product savedProduct = productService.createProduct(product);
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setDescription(savedProduct.getDescription());
        response.setSku(savedProduct.getSku());
        response.setPrice(savedProduct.getPrice());
        response.setCategoryId(savedProduct.getCategory().getId());
        response.setCategoryName(savedProduct.getCategory().getName());
        response.setActive(savedProduct.isActive());
        response.setCreatedAt(savedProduct.getCreatedAt());
        response.setUpdatedAt(savedProduct.getUpdatedAt());
        return response;
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        Product product  = productService.getProductById(id);
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setDescription(product.getDescription());
        response.setName(product.getName());
        response.setSku(product.getSku());
        response.setPrice(product.getPrice());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        response.setActive(product.isActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(product -> {
                    ProductResponse response = new ProductResponse();

                    response.setId(product.getId());
                    response.setName(product.getName());
                    response.setDescription(product.getDescription());
                    response.setSku(product.getSku());
                    response.setPrice(product.getPrice());
                    response.setCategoryId(product.getCategory().getId());
                    response.setCategoryName(product.getCategory().getName());
                    response.setActive(product.isActive());
                    response.setCreatedAt(product.getCreatedAt());
                    response.setUpdatedAt(product.getUpdatedAt());

                    return response;
                })
                .toList();
    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        Category category = new Category();
        category.setId(request.getCategoryId());
        product.setCategory(category);
        Product updatedProduct = productService.updateProduct(id , product);
        ProductResponse response = new ProductResponse();
        response.setId(updatedProduct.getId());
        response.setName(updatedProduct.getName());
        response.setDescription(updatedProduct.getDescription());
        response.setSku(updatedProduct.getSku());
        response.setPrice(updatedProduct.getPrice());
        response.setCategoryId(updatedProduct.getCategory().getId());
        response.setCategoryName(updatedProduct.getCategory().getName());
        response.setActive(updatedProduct.isActive());
        response.setCreatedAt(updatedProduct.getCreatedAt());
        response.setUpdatedAt(updatedProduct.getUpdatedAt());

        return response;
    }
}
