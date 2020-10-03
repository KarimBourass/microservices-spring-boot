package com.billing;

import com.billing.dao.BillRepository;
import com.billing.dao.ProductItemRepository;
import com.billing.model.Bill;
import com.billing.model.Customer;
import com.billing.model.Product;
import com.billing.model.ProductItem;
import com.billing.service.CustomerService;
import com.billing.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
							CustomerService customerService, InventoryService inventoryService){
		return args -> {
			Customer customer=customerService.findCustomerById(1L);
			System.out.println("*****************************");
			System.out.println("Le client = "+customer.toString());
			Bill bill=billRepository.save(new Bill(null, LocalDate.now(),customer.getId(),customer,null));

			PagedModel<Product> products=inventoryService.findAllProducts();
			for (Product p:products) {
				productItemRepository.save(new ProductItem(null,p.getId(),p,p.getPrice(),new Random().nextInt(10),bill));
			}

		};
	}
}
