package com.grp7.projectB.controller;

import com.grp7.projectB.domain.model.valueObjects.Product;
import com.grp7.projectB.repository.ProductDetailRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Autowired
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

    @PutMapping("/{product_id}")
    Product updateProduct(@PathVariable Long product_id,@RequestBody Product product) {
        Product updatedProduct = productRepository.findById(product_id).orElseThrow(RuntimeException::new);

        updatedProduct.setId(product_id);
        updatedProduct.setProductCategory(product.getProductCategory());
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setProductDetail(product.getProductDetail());
        return productRepository.save(updatedProduct);
    }

    @DeleteMapping("/{product_id}")
    void deleteProduct(@PathVariable Long product_id) { productRepository.deleteById(product_id); }
}
