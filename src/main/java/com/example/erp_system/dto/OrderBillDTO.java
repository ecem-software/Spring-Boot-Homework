package com.example.erp_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Data
public class OrderBillDTO {
    private UUID uuid;
    private CustomerOrderDTO order;
    private BigDecimal totalPrice;

    public OrderBillDTO() {

        this.uuid = UUID.randomUUID();
    }
}