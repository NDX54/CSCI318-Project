package com.grp7.projectC.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderEventSource {

    String ORDER_CREATION_OUTPUT = "orderCreationChannel";
    String ORDER_DELETION_OUTPUT = "orderDeletionChannel";

    @Output(ORDER_CREATION_OUTPUT)
    MessageChannel orderCreation();

    @Output(ORDER_DELETION_OUTPUT)
    MessageChannel orderDeletion();


}
