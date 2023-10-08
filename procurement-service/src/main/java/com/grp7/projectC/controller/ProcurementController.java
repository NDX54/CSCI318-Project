package com.grp7.projectC.controller;



import com.grp7.projectC.model.aggregates.ProductAggregate;
import com.grp7.projectC.model.aggregates.ProductId;
import com.grp7.projectC.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProcurementController {

    private final ProductService productService;

    ProcurementController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<String> create(@RequestBody ProductAggregate newProductAggregate) {
        productService.createProduct(newProductAggregate);
        return new ResponseEntity<>("Created new product: " + newProductAggregate, HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    ResponseEntity<String> update(@PathVariable ProductId productId, @RequestBody ProductAggregate productAggregate) {
        productService.updateProduct(productId, productAggregate);
        productAggregate.setProductId(productId);
        return new ResponseEntity<>("Updated product: " + productAggregate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    ResponseEntity<String>  delete(@PathVariable ProductId productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Deleted product: " + productId.toString(), HttpStatus.OK);
    }

    @GetMapping
    List<ProductAggregate> allProducts() { return productService.getAllProducts(); }

    @GetMapping("/get/{productId}")
    ProductAggregate find(@PathVariable ProductId productId) { return productService.getProduct(productId); }

}
