package com.grp7.projectC.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerEventSource {

    String CUSTOMER_CREATION_CHANNEL = "customerCreationChannel";
    String CUSTOMER_UPDATE_CHANNEL = "customerUpdateChannel";
    String CUSTOMER_DELETION_CHANNEL = "customerDeletionChannel";


    @Output(CUSTOMER_CREATION_CHANNEL)
    MessageChannel customerCreation();

    @Output(CUSTOMER_UPDATE_CHANNEL)
    MessageChannel customerUpdate();

    @Output(CUSTOMER_DELETION_CHANNEL)
    MessageChannel customerDeletion();
}
