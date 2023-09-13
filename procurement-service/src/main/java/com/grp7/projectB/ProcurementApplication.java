package com.grp7.projectB;

import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.events.ProductEvent;
import com.grp7.projectB.model.valueobjects.*;
import com.grp7.projectB.repository.ProductEventRepository;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
			prod1.setDescription(new Description("Choco drink."));
			prod1.setComment(new Comment("Yum!"));
			productRepository.save(prod1);

			ProductEvent productEvent1 = new ProductEvent();
			productEvent1.setEventName("Create");
			productEvent1.setProductId(new ProductId(productIdStr1));
			productEvent1.setProductCategory(prod1.getProductCategory());
			productEvent1.setProductName(prod1.getName());
			productEvent1.setProductPrice(prod1.getPrice());
			productEvent1.setDescription(prod1.getDescription());
			productEvent1.setComment(prod1.getComment());
			productEventRepository.save(productEvent1);
			System.out.println(productEvent1);

			ProductAggregate prod2 = new ProductAggregate();
			prod2.setProductId(new ProductId(productIdStr2));
			prod2.setProductCategory(new ProductCategory("Dried Goods"));
			prod2.setName(new Name("Cereal XYZ"));
			prod2.setPrice(new Price(7.99));
			prod2.setDescription(new Description("Fibrous Cereal"));
			prod2.setComment(new Comment("Makes you a take a sheit!"));
			productRepository.save(prod2);

			ProductEvent productEvent2 = new ProductEvent();
			productEvent2.setEventName("Create");
			productEvent2.setProductId(new ProductId(productIdStr2));
			productEvent2.setProductCategory(prod2.getProductCategory());
			productEvent2.setProductName(prod2.getName());
			productEvent2.setProductPrice(prod2.getPrice());
			productEvent2.setDescription(prod2.getDescription());
			productEvent2.setComment(prod2.getComment());
			productEventRepository.save(productEvent2);
			System.out.println(productEvent2);

		};
	}

}
