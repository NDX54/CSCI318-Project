package com.grp7.projectC.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Aggregate Identifier for the CustomerAggregate
 */

@Embeddable
public class CustomerId implements Serializable {
    @Column(name = "customer_id")
    private String customerId;

    public CustomerId() {
    }

    public CustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() { return customerId; }
}
