package com.inventoryservice;

import com.inventoryservice.dao.ProductRepository;
import com.inventoryservice.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository,
							RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Product.class);// Pour rendre lid visible lors du call d'un autre service
			productRepository.save(new Product(null ,"TRONE",10));
			productRepository.save(new Product(null ,"QALF",20));
			productRepository.save(new Product(null ,"33S",30));
		};
	}

}
