package com.grp7.projectB.controller;


import com.grp7.projectB.controller.dto.ProductDTO;
import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProcurementController {

    private final ProductService productService;

    ProcurementController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    void create(@RequestBody ProductAggregate newProductAggregate) { productService.createProduct(newProductAggregate); }

    @PutMapping("/{productId}")
    void update(@PathVariable ProductId productId, @RequestBody ProductAggregate productAggregate) {
        productService.updateProduct(productId, productAggregate);
    }

    @GetMapping
    List<ProductDTO> allProducts() {

        return productService.getAllProducts()
                .stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getProductId());
                    productDTO.setProductCategory(product.getProductCategory());
                    productDTO.setName(product.getName());
                    productDTO.setPrice(product.getPrice());
                    return productDTO;
                }).collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    ProductDTO findProduct(@PathVariable ProductId productId) {
        ProductDTO productDTO = new ProductDTO();
        ProductAggregate productAggregate = productService.getProduct(productId);
        productDTO.setProductId(productAggregate.getProductId());
        productDTO.setProductCategory(productAggregate.getProductCategory());
        productDTO.setName(productAggregate.getName());
        productDTO.setPrice(productAggregate.getPrice());
        return productDTO;
    }
}
