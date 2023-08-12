package com.grp7.projectA.controller;

import com.grp7.projectA.model.Product;
import com.grp7.projectA.model.ProductDetail;
import com.grp7.projectA.repository.ProductDetailRepository;
import com.grp7.projectA.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    ProductController(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    @GetMapping("/{product_id}")
    Product getProductById(@PathVariable Long product_id) { return productRepository.findById(product_id).orElseThrow(RuntimeException::new); }

    @GetMapping()
    List<Product> findAllProducts(@RequestParam(required = false, defaultValue = "false") boolean fetchDetails) {
        if (fetchDetails) {
            return productRepository.findAllWithProductDetail();
        } else {
            return productRepository.findAll();
        }
    }

    @PostMapping()
    Product createProduct(@RequestBody Product newProduct) { return productRepository.save(newProduct); }

    @DeleteMapping("/{product_id}")
    void deleteProduct(@PathVariable Long product_id) { productRepository.deleteById(product_id); }
}
