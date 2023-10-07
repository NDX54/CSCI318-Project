package com.grp7.projectC.service;

import com.grp7.projectC.brokers.ProductEventSource;
import com.grp7.projectC.controller.dto.ProductEventDTO;
import com.grp7.projectC.model.events.ProductEvent;
import com.grp7.projectC.repository.ProductEventRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Objects;

@Service
@EnableBinding(ProductEventSource.class)
public class ProcurementServiceEventHandler {

    private final ProductEventRepository productEventRepository;

    ProductEventSource productEventSource;

    ProcurementServiceEventHandler(ProductEventRepository productEventRepository, ProductEventSource productEventSource) {
        this.productEventRepository = productEventRepository;
        this.productEventSource = productEventSource;
    }

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleProductEvent(ProductEvent productEvent) {
        ProductEventDTO dto = new ProductEventDTO(productEvent.getEventName(),
                productEvent.getProductName().toString(),
                productEvent.getProductPrice().getPrice(),
                productEvent.getProductCategory().toString(),
                productEvent.getDescription().toString(),
                productEvent.getComment().toString(),
                productEvent.getStock().getStock(),
                productEvent.getProductId().toString()
                );
        if (Objects.equals(productEvent.getEventName(), ProductEvent.PRODUCT_CREATED)) {

            productEventSource.productCreation().send(MessageBuilder.withPayload(dto).build());

        } else if (Objects.equals(productEvent.getEventName(), ProductEvent.PRODUCT_UPDATED)) {

            productEventSource.productUpdate().send(MessageBuilder.withPayload(dto).build());

        } else if (Objects.equals(productEvent.getEventName(), ProductEvent.PRODUCT_DELETED)) {

            productEventSource.productDeletion().send(MessageBuilder.withPayload(dto).build());

        }
        productEventRepository.save(productEvent);
        System.out.println(dto);
    }

}
