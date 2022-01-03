package com.bootcamp.challenge.spring.dtos.products;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

/** DTO de Criação de produto
 *
 * @author Arthur Amorim
 *
 * */
public class ProductCreateDTO implements ConvertDTO<Product, ProductCreateDTO> {

    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Boolean freeshiping;
    private String prestige;

    /** Método usado para converter a entidade em um Product.
     *
     * @author Arthur Amorim
     *
     * @return Entidade Product
     *
     * */
    @Override
    public Product convert() {
        return new Product(name,category,brand,price,quantity,freeshiping,prestige);
    }

    /** Método usado para converter a entidade Product em um ProductCreateDTO.
     *
     * @author Arthur Amorim
     *
     * @param  product - Entidade Product a ser convertido.
     *
     * @return Filter convertido
     *
     * */
    @Override
    public ProductCreateDTO convert(Product product) {
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
