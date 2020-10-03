package com.billing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor  @AllArgsConstructor @ToString
@Entity
public class Bill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate billingDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long customerId;
    @Transient //ne pas enregistrer dans la base de donnée
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
}
