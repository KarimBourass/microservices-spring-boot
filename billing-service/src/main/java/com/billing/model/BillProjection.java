package com.billing.model;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.Collection;

@Projection(name="fullBill",types = Bill.class)
public interface BillProjection {
    public Long getId();
    public LocalDate getBillingDate();
    public Long getCustomerId();
    public Collection<ProductItem> getProductItems();
}
