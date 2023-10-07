package com.grp7.projectC.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductEventSource {

    String PRODUCT_CREATION_OUTPUT = "productCreationChannel";
    String PRODUCT_DELETION_OUTPUT = "productDeletionChannel";
    String PRODUCT_UPDATE_OUTPUT = "productUpdateChannel";

    @Output(PRODUCT_CREATION_OUTPUT)
    MessageChannel productCreation();

    @Output(PRODUCT_UPDATE_OUTPUT)
    MessageChannel productUpdate();

    @Output(PRODUCT_DELETION_OUTPUT)
    MessageChannel productDeletion();
}
