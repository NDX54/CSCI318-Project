package com.grp7.projectB;

import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.events.ProductCreatedEvent;
import com.grp7.projectB.model.valueobjects.Name;
import com.grp7.projectB.model.valueobjects.Price;
import com.grp7.projectB.model.valueobjects.ProductCategory;
import com.grp7.projectB.repository.ProductEventRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProcurementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) { return builder.build(); }

	@Bean
	public CommandLineRunner loadDatabase(ProductRepository productRepository, ProductEventRepository productEventRepository) throws Exception {
		return args -> {

			String random1 = UUID.randomUUID().toString().toUpperCase();
			String random2 = UUID.randomUUID().toString().toUpperCase();
			String productIdStr1 = random1.substring(0, random1.indexOf("-"));
			String productIdStr2 = random2.substring(0, random2.indexOf("-"));


			ProductAggregate prod1 = new ProductAggregate();
			prod1.setProductId(new ProductId(productIdStr1));
			prod1.setProductCategory(new ProductCategory("Drinks"));
			prod1.setName(new Name("Up & Go! Chocolate Flavour"));
			prod1.setPrice(new Price(6.99));
			productRepository.save(prod1);

			ProductCreatedEvent productCreatedEvent1 = new ProductCreatedEvent();
			productCreatedEvent1.setEventName("Create");
			productCreatedEvent1.setProductId(new ProductId(productIdStr1));
			productCreatedEvent1.setProductCategory(prod1.getProductCategory());
			productCreatedEvent1.setProductName(prod1.getName());
			productCreatedEvent1.setProductPrice(prod1.getPrice());
			productEventRepository.save(productCreatedEvent1);
			System.out.println(productCreatedEvent1);

			ProductAggregate prod2 = new ProductAggregate();
			prod2.setProductId(new ProductId(productIdStr2));
			prod2.setProductCategory(new ProductCategory("Dried Goods"));
			prod2.setName(new Name("Cereal XYZ"));
			prod2.setPrice(new Price(7.99));
			productRepository.save(prod2);

			ProductCreatedEvent productCreatedEvent2 = new ProductCreatedEvent();
			productCreatedEvent2.setEventName("Create");
			productCreatedEvent2.setProductId(new ProductId(productIdStr2));
			productCreatedEvent2.setProductCategory(prod2.getProductCategory());
			productCreatedEvent2.setProductName(prod2.getName());
			productCreatedEvent2.setProductPrice(prod2.getPrice());
			productEventRepository.save(productCreatedEvent2);
			System.out.println(productCreatedEvent2);

		};
	}

}
