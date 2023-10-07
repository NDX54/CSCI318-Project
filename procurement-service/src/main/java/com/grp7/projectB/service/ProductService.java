package com.grp7.projectB.service;

import com.grp7.projectB.controller.dto.OrderEventDTO;
import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.events.ProductEvent;
import com.grp7.projectB.model.valueobjects.Stock;
import com.grp7.projectB.repository.ProductEventRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductEventRepository productEventRepository;

//    private final RestTemplate restTemplate;

    private final ApplicationEventPublisher applicationEventPublisher;

    private KafkaTemplate<String, ProductEvent> kafkaTemplate;

    ProductService(ProductRepository productRepository,
                   ProductEventRepository productEventRepository,
                   ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.productEventRepository = productEventRepository;
//        this.restTemplate = restTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<ProductAggregate> getAllProducts() { return productRepository.findAll(); }

    public ProductAggregate getProduct(ProductId productId) { return productRepository.findByProductId(productId).orElseThrow(EntityNotFoundException::new); }
    @Transactional
    public void createProduct(ProductAggregate newProductAggregate) {

        String random = UUID.randomUUID().toString().toUpperCase();
        String productIdStr = random.substring(0, random.indexOf("-"));
        newProductAggregate.setProductId(new ProductId(productIdStr));

        productRepository.save(newProductAggregate);


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

    }

    @Transactional
    public void updateProduct(ProductId productId, ProductAggregate productAggregate) {
        ProductAggregate existingProductAggregate = productRepository.findByProductId(productId).orElseThrow(EntityNotFoundException::new);
        existingProductAggregate.setProductCategory(productAggregate.getProductCategory());
        existingProductAggregate.setName(productAggregate.getName());
        existingProductAggregate.setPrice(productAggregate.getPrice());
        existingProductAggregate.setDescription(productAggregate.getDescription());
        existingProductAggregate.setComment(productAggregate.getComment());
        existingProductAggregate.setStock(productAggregate.getStock());

        productRepository.save(existingProductAggregate);


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
    }

    @Transactional
    public void deleteProduct(ProductId productId) {
        ProductAggregate productToDelete = productRepository.findByProductId(productId).orElseThrow(EntityNotFoundException::new);

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
