package com.yash.ecommerce_backend.product.repository;

import com.yash.ecommerce_backend.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
    boolean existsBySkuAndIdNot(String sku, Long id);
    Page<Product> findByCategoryId(Long CategoryId, Pageable pageable);
    Page<Product> findByPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable
    );
    Page<Product> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
