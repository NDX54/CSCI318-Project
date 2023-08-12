package com.grp7.projectA.controller;

import com.grp7.projectA.model.Product;
import com.grp7.projectA.model.ProductDetail;
import com.grp7.projectA.repository.ProductDetailRepository;
import com.grp7.projectA.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductDetailController {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    ProductDetailController(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    @PostMapping("/products/{product_id}/product-details")
    ProductDetail createProductDetail(@PathVariable Long product_id, @RequestBody ProductDetail productDetail) {
        Product product = productRepository.findById(product_id).orElseThrow(RuntimeException::new);

        productDetail.setProduct(product);

        return productDetailRepository.save(productDetail);
    }

    @PutMapping("/products/{product_id}/product-details/{product_detail_id}")
    Product updateProductDetail(@PathVariable Long product_id, @PathVariable Long product_detail_id) {
        Product product = productRepository.findById(product_id).orElseThrow(RuntimeException::new);
        ProductDetail productDetail = productDetailRepository.findById(product_detail_id).orElseThrow(RuntimeException::new);
        product.setProductDetail(productDetail);
        return productRepository.save(product);
    }
}
