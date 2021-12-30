package com.bootcamp.challenge.spring.entities;

import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/** Entidade Produto
 *
 * @author Lorraine Mendes
 * @author Samuel Stalschus
 * @author Arthur Amorim
 *
 * */
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

    /** Construtor completo
     *
     * @author Arthur Amorim
     *
     * */
    public Product(String name, String category, String brand, BigDecimal price, Integer quantity, Boolean freeshiping, String prestige) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.freeshiping = freeshiping;
        this.prestige = prestige;
    }


    /** Construtor que recebe apenas id e quantidade
     *
     * @author Arthur Amorim
     *
     * @param  id - Id do produto
     * @param  quantity - Id da quantidade
     *
     * */
    public Product(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    /** Sobrecarga do método equals para fazer a comparação dos produtos com base no ID
     *
     * @author Samuel Stalschus
     *
     * @param  obj - Objeto genêrico
     *
     * @return Bollean
     *
     * */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)return true;
        if (!(obj instanceof Product)) return false;
        Product emp = (Product) obj;
        return id.equals(emp.id);
    }
}
