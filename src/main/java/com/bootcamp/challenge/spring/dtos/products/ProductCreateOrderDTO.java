package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductCreateOrderDTO implements ConvertDTO<Product, ProductCreateOrderDTO> {

    private Long id;
    private Integer quantity;

    @Override
    public Product convert() {
        return new Product(id, quantity);
    }

    @Override
    public ProductCreateOrderDTO convert(Product product) {
        this.id = product.getId();
        this.quantity = product.getQuantity();
        return null;
    }
}
