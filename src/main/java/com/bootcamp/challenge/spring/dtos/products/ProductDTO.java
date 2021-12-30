package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements ConvertDTO<Product, ProductDTO> {

    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeshiping;
    private String prestige;

    @Override
    public Product convert() {
        return new Product(name, category, brand, price, quantity,freeshiping, prestige);
    }
    public Integer getTotalMultiplicacao() {
        return 100*10;
    }

    public String getDescription() {
        return "descricao do produto";
    }

    @Override
    public ProductDTO convert(Product product) {
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
