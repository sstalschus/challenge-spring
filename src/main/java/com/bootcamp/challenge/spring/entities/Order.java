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
public class Order {
    private Long id;
    private BigDecimal totalValue;
    private List<Product> productList;

    public Order(List<Product> productList) {
        this.productList = productList;
    }
}
