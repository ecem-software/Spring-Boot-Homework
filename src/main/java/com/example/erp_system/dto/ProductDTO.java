package com.example.erp_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Data
public class ProductDTO {

    private UUID uuid;
    private String name;
    private Integer stockAmount;
    private BigDecimal productPrice;
    private Boolean isKdvApplied;
    private BigDecimal nonKdvAppliedPrice = BigDecimal.ZERO;
    private int orderCount;
    private KdvDTO kdv;
    private CustomerOrderDTO order; //!!!!

    public ProductDTO() {

        this.uuid = UUID.randomUUID();
    }
}
