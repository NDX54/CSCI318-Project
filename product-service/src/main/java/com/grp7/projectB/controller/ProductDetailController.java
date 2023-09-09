package com.grp7.projectB.controller;

import com.grp7.projectB.domain.model.Product;
import com.grp7.projectB.domain.model.ProductDetail;
import com.grp7.projectB.repository.ProductDetailRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductDetailController {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Autowired
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
    ProductDetail updateProductDetail(@PathVariable Long product_id, @PathVariable Long product_detail_id, @RequestBody ProductDetail productDetail) {
        Product product = productRepository.findById(product_id).orElseThrow(RuntimeException::new);
        ProductDetail updatedProductDetail = productDetailRepository.findById(product_detail_id).orElseThrow(RuntimeException::new);

        updatedProductDetail.setProduct(product);
        updatedProductDetail.setId(product_detail_id);
        updatedProductDetail.setComment(productDetail.getComment());
        updatedProductDetail.setDescription(productDetail.getDescription());
        return productDetailRepository.save(updatedProductDetail);
    }
}
