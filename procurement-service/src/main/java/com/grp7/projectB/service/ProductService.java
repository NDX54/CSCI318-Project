package com.grp7.projectB.service;


import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.events.ProductCreatedEvent;
import com.grp7.projectB.repository.ProductEventRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductEventRepository productEventRepository;
    private final RestTemplate restTemplate;

    private final ApplicationEventPublisher applicationEventPublisher;

    ProductService(ProductRepository productRepository, ProductEventRepository productEventRepository, RestTemplate restTemplate,
                   ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.productEventRepository = productEventRepository;
        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<ProductAggregate> getAllProducts() { return productRepository.findAll(); }

    public ProductAggregate getProduct(String productId) { return productRepository.findById(productId).orElseThrow(RuntimeException::new); }

    public ProductAggregate getProduct(ProductId productId) { return productRepository.findByProductId(productId).orElseThrow(RuntimeException::new); }

    @Transactional
    public void createProduct(ProductAggregate newProductAggregate) {

        String random = UUID.randomUUID().toString().toUpperCase();
        String productIdStr = random.substring(0, random.indexOf("-"));
        newProductAggregate.setProductId(new ProductId(productIdStr));

        productRepository.save(newProductAggregate);


        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        productCreatedEvent.setEventName("Create");
        productCreatedEvent.setProductId(new ProductId(productIdStr));
        productCreatedEvent.setProductCategory(newProductAggregate.getProductCategory());
        productCreatedEvent.setProductName(newProductAggregate.getName());
        productCreatedEvent.setProductPrice(newProductAggregate.getPrice());

        applicationEventPublisher.publishEvent(productCreatedEvent);

    }

    @Transactional
    public void updateProduct(ProductId productId, ProductAggregate productAggregate) {
        ProductAggregate existingProductAggregate = productRepository.findByProductId(productId).orElseThrow(RuntimeException::new);
        existingProductAggregate.setProductId(productId);
        existingProductAggregate.setProductCategory(productAggregate.getProductCategory());
        existingProductAggregate.setName(productAggregate.getName());
        existingProductAggregate.setPrice(productAggregate.getPrice());

        productRepository.save(existingProductAggregate);
    }

}
