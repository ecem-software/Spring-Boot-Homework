package com.example.erp_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "product_uuid"))
public class ProductEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private Boolean isKdvApplied;
    @Column
    private Integer stockAmount;
    @Column
    private BigDecimal productPrice;
    @Column
    private BigDecimal nonKdvAppliedPrice=BigDecimal.ZERO;
    @Column
    private int orderCount;
    @ManyToOne
    @JoinColumn(name = "kdvId", unique = false)
    private KdvEntity kdv;
}
