package com.billing.web.controller;

import com.billing.dao.BillRepository;
import com.billing.dao.ProductItemRepository;
import com.billing.model.Bill;
import com.billing.service.CustomerService;
import com.billing.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(p->
                p.setProduct(inventoryService.findProductById(p.getProductId())));
        return bill;
    }



}
