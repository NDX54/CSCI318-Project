package com.grp7.projectC.applicationservice;


import com.grp7.projectC.applicationservice.dto.CustomerEventDTO;
import com.grp7.projectC.applicationservice.dto.ProductEventDTO;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.javatuples.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.function.Consumer;

@Configuration
public class StreamProcessor {

    public final static String CUSTOMER_STATE_STORE = "customers";

    public final static String PRODUCT_STATE_STORE = "products";

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

    @Bean
    public Consumer<KStream<String, ProductEventDTO>> processProductUpdate() {

        return inputStream -> {
            KStream<String, Long> aggregatedStream = inputStream.map((key, value) -> {
                String productId = value.getProductId();
                Long stock = value.getStock().longValue();
                return KeyValue.pair(productId, stock);
            });

            aggregatedStream.print(Printed.<String, Long>toSysOut().withLabel("Stock remaining for product"));
        };
    }

//    @Bean
//    public Consumer<KStream<String, ProductEventDTO>> processProductUpdate() {
//
//        return inputStream -> {
//            KStream<String, Pair<String, Long>> aggregatedStream = inputStream.map((key, value) -> {
//                        String productId = value.getProductId();
//                        String productName = value.getProductName();
//                        Long stock = value.getStock().longValue();
//                        return KeyValue.pair(productId, Pair.with(productName, stock));
//                    })
//                    .groupByKey(Grouped.with(Serdes.String(), Pair.with(Serdes.String(), Serdes.Long())))
//                    .reduce(Long::sum)
//                    .toStream();
//
//            aggregatedStream.print(Printed.<String, Long>toSysOut().withLabel("Stock remaining for product"));
//        };
//    }

}
