package com.grp7.projectB;

import com.grp7.projectB.model.aggregates.ProductAggregate;
import com.grp7.projectB.model.aggregates.ProductId;
import com.grp7.projectB.model.valueobjects.Name;
import com.grp7.projectB.model.valueobjects.Price;
import com.grp7.projectB.model.valueobjects.ProductCategory;
import com.grp7.projectB.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) { return builder.build(); }

	@Bean
	public CommandLineRunner loadDatabase(ProductRepository productRepository) throws Exception {
		return args -> {
			ProductAggregate prod1 = new ProductAggregate();
			prod1.setProductId(new ProductId("uuoe"));
			prod1.setProductCategory(new ProductCategory("Drinks"));
			prod1.setName(new Name("Up & Go! Chocolate Flavour"));
			prod1.setPrice(new Price(6.99));
			productRepository.save(prod1);

			ProductAggregate prod2 = new ProductAggregate();
			prod2.setProductId(new ProductId("jhfo"));
			prod2.setProductCategory(new ProductCategory("Dried Goods"));
			prod2.setName(new Name("Cereal XYZ"));
			prod2.setPrice(new Price(7.99));
			productRepository.save(prod2);

		};
	}

}
