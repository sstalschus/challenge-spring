package com.bootcamp.challenge.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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

    public Product(String name, String category, String brand, BigDecimal price, Integer quantity, Boolean freeshiping, String prestige) {
            this.name = name;
            this.category = category;
            this.brand = brand;
            this.price = price;
            this.quantity = quantity;
        this.freeshiping = freeshiping;
        this.prestige = prestige;
    }

    public Product(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)return true;
        if (!(obj instanceof Product)) return false;
        Product emp = (Product) obj;
        return id.equals(emp.id);
    }


}
