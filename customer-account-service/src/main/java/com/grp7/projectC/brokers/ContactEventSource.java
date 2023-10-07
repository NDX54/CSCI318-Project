package com.grp7.projectC.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ContactEventSource {

    String CONTACT_CREATION_CHANNEL = "contactCreationChannel";
    String CONTACT_UPDATE_CHANNEL = "contactUpdateChannel";
    String CONTACT_DELETION_CHANNEL = "contactDeletionChannel";


    @Output(CONTACT_CREATION_CHANNEL)
    MessageChannel contactCreation();

    @Output(CONTACT_UPDATE_CHANNEL)
    MessageChannel contactUpdate();

    @Output(CONTACT_DELETION_CHANNEL)
    MessageChannel contactDeletion();
}
