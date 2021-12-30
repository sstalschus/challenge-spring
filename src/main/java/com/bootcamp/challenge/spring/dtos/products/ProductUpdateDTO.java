package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO implements ConvertDTO<Product, ProductUpdateDTO> {

    private Long id;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeshiping;
    private String prestige;

    @Override
    public Product convert() {
        return new Product(id, name, category, brand, price, quantity,freeshiping, prestige);
    }

    @Override
    public ProductUpdateDTO convert(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.freeshiping = product.getFreeshiping();
        this.prestige = product.getPrestige();
        return this;
    }
}
