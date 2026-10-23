package com.yash.ecommerce_backend.product.service;

import com.yash.ecommerce_backend.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    public List<Product> getAllProducts();
    Product updateProduct(Long id , Product product);
    void deleteProduct(Long id);
    Page<Product> getAllProducts(Pageable pageable);
}
