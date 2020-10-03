package com.customer;

import com.customer.dao.CustomerRepository;
import com.customer.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);// Pour rendre lid visible lors du call d'un autre service
			customerRepository.save(new Customer(null,"karim","karim@gmail.com"));
			customerRepository.save(new Customer(null,"mehdi","mehdi@gmail.com"));
			customerRepository.save(new Customer(null,"amine","amine@gmail.com"));
		};
	}

}
