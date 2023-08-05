package com.example.erp_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class CustomerDTO {
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private String customerNo;
    public CustomerDTO() {
        this.uuid = UUID.randomUUID();
    }
}
