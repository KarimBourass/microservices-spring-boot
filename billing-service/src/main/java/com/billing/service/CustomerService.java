package com.billing.service;

import com.billing.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerService {

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable(name="id") Long id);
}
