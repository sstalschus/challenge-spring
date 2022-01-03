package com.bootcamp.challenge.spring.dtos.products;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.*;

import java.math.BigDecimal;

/** DTO de Criação de product
 *
 * @author Arthur Amorim
 *
 * */
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

    /** Método usado para converter a entidade em um Produto.
     *
     * @author Arthur Amorim
     *
     * @return Product
     *
     * */
    @Override
    public Product convert() {
        return new Product(name, category, brand, price, quantity,freeshiping, prestige);
    }

    /** Método usado para converter a entidade product em um ProductDTO.
     *
     * @author Arthur Amorim
     *
     * @param  product - Entidade Product a ser convertida em ProductDTO.
     *
     * @return ProductDTO convertido
     *
     * */
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
