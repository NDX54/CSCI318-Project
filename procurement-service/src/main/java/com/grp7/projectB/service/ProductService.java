package com.grp7.projectB.service;


import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    ProductService(ProductRepository productRepository, RestTemplate restTemplate,
                   ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<ProductAggregate> getAllProducts() { return productRepository.findAll(); }

    public ProductAggregate getProduct(String productId) { return productRepository.findById(productId).orElseThrow(RuntimeException::new); }

    public ProductAggregate getProduct(ProductId productId) { return productRepository.findByProductId(productId).orElseThrow(RuntimeException::new); }

    public void createProduct(ProductAggregate newProductAggregate) { productRepository.save(newProductAggregate); }

    public void updateProduct(ProductId productId, ProductAggregate productAggregate) {
        ProductAggregate existingProductAggregate = productRepository.findByProductId(productId).orElseThrow(RuntimeException::new);
        existingProductAggregate.setProductId(productId);
        existingProductAggregate.setProductCategory(productAggregate.getProductCategory());
        existingProductAggregate.setName(productAggregate.getName());
        existingProductAggregate.setPrice(productAggregate.getPrice());

        productRepository.save(existingProductAggregate);
    }

}
