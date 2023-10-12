package com.grp7.projectC.applicationservice;

import com.grp7.projectC.applicationservice.dto.CustomerEventDTO;
import com.grp7.projectC.applicationservice.dto.OrderEventDTO;
import com.grp7.projectC.applicationservice.dto.ProductEventDTO;
import com.grp7.projectC.shareddomain.brokers.AnalyticsSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(AnalyticsSource.class)
public class AnalyticsService {

    @StreamListener(AnalyticsSource.CUSTOMER_CREATION_INPUT)
    public void receiveCustomerCreatedEvent(CustomerEventDTO customerEventDTO) {

        if (customerEventDTO == null) {
            System.out.println("ERROR: Received null customerEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC customerCreations: " +
                customerEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.CUSTOMER_UPDATE_INPUT)
    public void receiveCustomerUpdatedEvent(CustomerEventDTO customerEventDTO) {

        if (customerEventDTO == null) {
            System.out.println("ERROR: Received null customerEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC customerUpdates: " +
                customerEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.CUSTOMER_DELETION_INPUT)
    public void receiveCustomerDeletedEvent(CustomerEventDTO customerEventDTO) {

        if (customerEventDTO == null) {
            System.out.println("ERROR: Received null CustomerEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC customerDeletions: " +
                customerEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.PRODUCT_CREATION_INPUT)
    public void receiveProductCreatedEvent(ProductEventDTO productEventDTO) {

        if (productEventDTO == null) {
            System.out.println("ERROR: Received null productEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC productCreations: " +
                productEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.PRODUCT_UPDATE_INPUT)
    public void receiveProductUpdatedEvent(ProductEventDTO productEventDTO) {

        if (productEventDTO == null) {
            System.out.println("ERROR: Received null productEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC productUpdates: " +
                productEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.PRODUCT_DELETION_INPUT)
    public void receiveProductDeletedEvent(ProductEventDTO productEventDTO) {

        if (productEventDTO == null) {
            System.out.println("ERROR: Received null productEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC productDeletions: " +
                productEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.ORDER_CREATION_INPUT)
    public void receiveOrderCreatedEvent(OrderEventDTO orderEventDTO) {

        if (orderEventDTO == null) {
            System.out.println("ERROR: Received null orderEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC orderCreations: " +
                orderEventDTO + "****"
        );
    }

    @StreamListener(AnalyticsSource.ORDER_DELETION_INPUT)
    public void receiveOrderDeletedEvent(OrderEventDTO orderEventDTO) {

        if (orderEventDTO == null) {
            System.out.println("ERROR: Received null orderEventDTO");
            return;
        }

        System.out.println("****READING FROM KAFKA TOPIC orderDeletions: " +
                orderEventDTO + "****"
        );
    }
}
