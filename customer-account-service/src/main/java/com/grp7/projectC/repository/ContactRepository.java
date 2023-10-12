package com.grp7.projectC.repository;

import com.grp7.projectC.model.aggregates.CustomerAggregate;
import com.grp7.projectC.model.aggregates.CustomerId;
import com.grp7.projectC.model.entities.Contact;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    Optional<Contact> findByContactId(String contactId);

    List<Contact> findAllByCustomer(CustomerAggregate customerAggregate);

    @Query("SELECT c FROM Contact c WHERE c.customer.customerId.customerId = :customerId")
    List<Contact> findAllByCustomerId(String customerId);

}