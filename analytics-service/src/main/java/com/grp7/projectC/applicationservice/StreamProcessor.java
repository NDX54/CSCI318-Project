package com.grp7.projectC.applicationservice;


import com.grp7.projectC.applicationservice.dto.CustomerEventDTO;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamProcessor {

    public final static String CUSTOMER_STATE_STORE = "customers";

    @Bean
    public Consumer<KStream<String, CustomerEventDTO>> processOrderCreation() {

        return inputStream -> {
            KStream<String, Long> aggregatedStream = inputStream.map((key, value) -> {
                String customerID = value.getCustomerId();
                Long numberOfOrdersMade = value.getOrdersMade().longValue();
                return KeyValue.pair(customerID, numberOfOrdersMade);
            }).
                    groupByKey(Grouped.with(Serdes.String(), Serdes.Long())).
                    reduce(Long::sum).toStream();

            aggregatedStream.print(Printed.<String, Long>toSysOut().withLabel("Total number of orders made by customerID"));
        };
    }

//    @Bean
//    public Consumer<KStream<String, OrderEvent>> processOrderDeletion() {
//
//        return inputStream -> {
//            KStream<String, Long> aggregatedStream = inputStream.map((key, value) -> {
//                        String productID = value.getOrderEventData().getProductId();
//                        Long orderQuantity = value.getOrderEventData().getQuantity().longValue();
//                        return KeyValue.pair(productID, orderQuantity);
//                    }).
//                    groupByKey(Grouped.with(Serdes.String(), Serdes.Long())).
//                    reduce(Long::sum).toStream();
//
//            aggregatedStream.print(Printed.<String, Long>toSysOut().withLabel("Total quantity by productID"));
//        };
//    }

}
