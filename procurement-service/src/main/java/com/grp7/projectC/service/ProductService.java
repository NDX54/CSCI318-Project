package com.grp7.projectC.service;

import com.grp7.projectC.errorhandlers.CustomNotFoundException;
import com.grp7.projectC.model.aggregates.ProductAggregate;
import com.grp7.projectC.model.aggregates.ProductId;
import com.grp7.projectC.model.events.ProductEvent;
import com.grp7.projectC.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    ProductService(ProductRepository productRepository,
                   ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<ProductAggregate> getAllProducts() { return productRepository.findAll(); }

    public ProductAggregate getProduct(ProductId productId) { return productRepository.findByProductId(productId).orElseThrow(() -> new CustomNotFoundException("Product not found")); }
    @Transactional
    public ProductAggregate createProduct(ProductAggregate newProductAggregate) {

        String random = UUID.randomUUID().toString().toUpperCase();
        String productIdStr = random.substring(0, random.indexOf("-"));
        newProductAggregate.setProductId(new ProductId(productIdStr));

//        productRepository.save(newProductAggregate);


        ProductEvent productEvent = new ProductEvent();

        productEvent.setEventName(ProductEvent.PRODUCT_CREATED);
        productEvent.setProductId(new ProductId(productIdStr));
        productEvent.setProductCategory(newProductAggregate.getProductCategory());
        productEvent.setProductName(newProductAggregate.getName());
        productEvent.setProductPrice(newProductAggregate.getPrice());
        productEvent.setDescription(newProductAggregate.getDescription());
        productEvent.setComment(newProductAggregate.getComment());
        productEvent.setStock(newProductAggregate.getStock());

        applicationEventPublisher.publishEvent(productEvent);

        return productRepository.save(newProductAggregate);
    }

    @Transactional
    public ProductAggregate updateProduct(ProductId productId, ProductAggregate productAggregate) {
        ProductAggregate existingProductAggregate = productRepository.findByProductId(productId).orElseThrow(() -> new CustomNotFoundException("Product not found"));
        existingProductAggregate.setProductCategory(productAggregate.getProductCategory());
        existingProductAggregate.setName(productAggregate.getName());
        existingProductAggregate.setPrice(productAggregate.getPrice());
        existingProductAggregate.setDescription(productAggregate.getDescription());
        existingProductAggregate.setComment(productAggregate.getComment());
        existingProductAggregate.setStock(productAggregate.getStock());


        ProductEvent productEvent = new ProductEvent();

        productEvent.setEventName(ProductEvent.PRODUCT_UPDATED);
        productEvent.setProductId(productId);
        productEvent.setProductCategory(productAggregate.getProductCategory());
        productEvent.setProductName(productAggregate.getName());
        productEvent.setProductPrice(productAggregate.getPrice());
        productEvent.setDescription(productAggregate.getDescription());
        productEvent.setComment(productAggregate.getComment());
        productEvent.setStock(productAggregate.getStock());

        applicationEventPublisher.publishEvent(productEvent);

        return productRepository.save(existingProductAggregate);
    }

    @Transactional
    public void deleteProduct(ProductId productId) {
        ProductAggregate productToDelete = productRepository.findByProductId(productId).orElseThrow(() -> new CustomNotFoundException("Product not found"));

        ProductEvent productEvent = new ProductEvent();

        productEvent.setEventName(ProductEvent.PRODUCT_DELETED);
        productEvent.setProductId(productId);
        productEvent.setProductCategory(productToDelete.getProductCategory());
        productEvent.setProductName(productToDelete.getName());
        productEvent.setProductPrice(productToDelete.getPrice());
        productEvent.setDescription(productToDelete.getDescription());
        productEvent.setComment(productToDelete.getComment());
        productEvent.setStock(productToDelete.getStock());

        applicationEventPublisher.publishEvent(productEvent);

        productRepository.deleteByProductId(productId);
    }

}
