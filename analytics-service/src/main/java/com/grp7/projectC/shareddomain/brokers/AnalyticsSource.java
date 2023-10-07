package com.grp7.projectC.shareddomain.brokers;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AnalyticsSource {

    String ORDER_CREATION_INPUT = "orderCreationChannel";
    String ORDER_DELETION_INPUT = "orderDeletionChannel";

    String CUSTOMER_CREATION_INPUT = "customerCreationChannel";
    String CUSTOMER_UPDATE_INPUT = "customerUpdateChannel";
    String CUSTOMER_DELETION_INPUT = "customerDeletionChannel";

    String PRODUCT_CREATION_INPUT = "productCreationChannel";
    String PRODUCT_DELETION_INPUT = "productDeletionChannel";
    String PRODUCT_UPDATE_INPUT = "productUpdateChannel";


    @Input(PRODUCT_CREATION_INPUT)
    SubscribableChannel productCreation();

    @Input(PRODUCT_UPDATE_INPUT)
    SubscribableChannel productUpdate();

    @Input(PRODUCT_DELETION_INPUT)
    SubscribableChannel productDeletion();


    @Input(CUSTOMER_CREATION_INPUT)
    SubscribableChannel customerCreation();

    @Input(CUSTOMER_UPDATE_INPUT)
    SubscribableChannel customerUpdate();

    @Input(CUSTOMER_DELETION_INPUT)
    SubscribableChannel customerDeletion();


    @Input(ORDER_CREATION_INPUT)
    SubscribableChannel orderCreation();

    @Input(ORDER_DELETION_INPUT)
    SubscribableChannel orderDeletion();




}
