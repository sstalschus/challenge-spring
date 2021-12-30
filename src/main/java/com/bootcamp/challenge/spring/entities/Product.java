package com.bootcamp.challenge.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeshiping;
    private String prestige;


    @Override
    public boolean equals(Object obj) {
        if (obj == this)return true;
        if (!(obj instanceof Product)) return false;
        Product emp = (Product) obj;
        return id.equals(emp.id);
    }
}
