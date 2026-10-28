package com.yash.ecommerce_backend.product.service;

import com.yash.ecommerce_backend.product.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Long id , Product product);
    void deleteProduct(Long id);
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> getProductsByCategory(Long categoryId, Pageable pageable);
    Page<Product> getProductsByPriceRange(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable
    );
    Page<Product> searchProductsByName(String name , Pageable pageable);
}
