package com.yash.ecommerce_backend.product.service.impl;
import com.yash.ecommerce_backend.category.entity.Category;
import com.yash.ecommerce_backend.category.repository.CategoryRepository;
import com.yash.ecommerce_backend.product.entity.Product;
import com.yash.ecommerce_backend.product.repository.ProductRepository;
import com.yash.ecommerce_backend.product.service.ProductService;
import org.springframework.stereotype.Service;
import com.yash.ecommerce_backend.common.exception.ProductNotFoundException;
import com.yash.ecommerce_backend.common.exception.CategoryNotFoundException;
import com.yash.ecommerce_backend.common.exception.DuplicateSkuException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository
    ,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
       Long categoryId = product.getCategory().getId();
       Category category = categoryRepository.findById(categoryId)
               .orElseThrow(()->new RuntimeException(
                       "Category not found with id " + categoryId)
               );
       product.setCategory(category);
       if(productRepository.existsBySku(product.getSku())){
           throw new DuplicateSkuException(
                   "Product already exists with sku " + product.getSku()
           );
       }
       return productRepository.save(product);
    }
    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id " + id));
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public Product updateProduct(Long id , Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(
                        "Product not Found with id " + id
                ));
        Long categoryId = product.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(()-> new CategoryNotFoundException(
                                "Category not found with id " + categoryId
                        ));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setSku(product.getSku());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(category);
        if(productRepository.existsBySkuAndIdNot(
                product.getSku(),id)){
            throw new DuplicateSkuException(
                    "Sku already used by another product: "
                            +product.getSku()
            );
        }

        return productRepository.save(existingProduct);
    }
    @Override
    public void deleteProduct(Long id){
        Product product  = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(
                        "Product not found with id " + id));
            productRepository.delete(product);
    }
    @Override
    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

}
