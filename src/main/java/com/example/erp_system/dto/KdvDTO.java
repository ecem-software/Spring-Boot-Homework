package com.example.erp_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Data
public class KdvDTO {
    private UUID uuid;
    private String name;
    private BigDecimal percent;

    public KdvDTO() {

        this.uuid = UUID.randomUUID();
    }
}
