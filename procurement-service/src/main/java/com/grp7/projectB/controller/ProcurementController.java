package com.grp7.projectB.controller;



import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @PutMapping("/update/{productId}")
    void update(@PathVariable ProductId productId, @RequestBody ProductAggregate productAggregate) { productService.updateProduct(productId, productAggregate); }

    @DeleteMapping("/delete/{productId}")
    void delete(@PathVariable ProductId productId) { productService.deleteProduct(productId); }

    @GetMapping
    List<ProductAggregate> allProducts() { return productService.getAllProducts(); }

    @GetMapping("/get/{productId}")
    ProductAggregate find(@PathVariable ProductId productId) { return productService.getProduct(productId); }

}
