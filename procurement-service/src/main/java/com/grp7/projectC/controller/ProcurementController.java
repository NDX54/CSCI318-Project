package com.grp7.projectC.controller;



import com.grp7.projectC.customresponses.APIResponse;
import com.grp7.projectC.model.aggregates.ProductAggregate;
import com.grp7.projectC.model.aggregates.ProductId;
import com.grp7.projectC.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProcurementController {

    private final ProductService productService;

    ProcurementController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<APIResponse<ProductAggregate>> create(@Valid @RequestBody ProductAggregate newProductAggregate, WebRequest request) {
        ProductAggregate newProduct = productService.createProduct(newProductAggregate);

        APIResponse<ProductAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product created successfully");
        response.setDetails(newProduct);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    ResponseEntity<APIResponse<ProductAggregate>> update(@PathVariable ProductId productId, @Valid @RequestBody ProductAggregate productAggregate, WebRequest request) {
        ProductAggregate updatedProduct = productService.updateProduct(productId, productAggregate);

        APIResponse<ProductAggregate> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product updated successfully");
        response.setDetails(updatedProduct);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    ResponseEntity<APIResponse<ProductId>> delete(@PathVariable ProductId productId, WebRequest request) {
        productService.deleteProduct(productId);

        APIResponse<ProductId> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product deleted successfully");
        response.setDetails(productId);
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    List<ProductAggregate> allProducts() { return productService.getAllProducts(); }

    @GetMapping("/get/{productId}")
    ProductAggregate find(@PathVariable ProductId productId) { return productService.getProduct(productId); }

}
