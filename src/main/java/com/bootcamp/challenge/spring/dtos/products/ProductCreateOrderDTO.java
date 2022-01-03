package com.bootcamp.challenge.spring.dtos.products;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO de Criação de pedido
 *
 * @author Arthur Amorim
 *
 * */
@NoArgsConstructor
@Getter
@Setter
public class ProductCreateOrderDTO implements ConvertDTO<Product, ProductCreateOrderDTO> {

    private Long id;
    private Integer quantity;

    /** Método usado para converter a entidade em um Produto.
     *
     * @author Arthur Amorim
     *
     * @return Product convertido
     *
     * */
    @Override
    public Product convert() {
        return new Product(id, quantity);
    }

    /** Método usado para converter a entidade product em um ProductCreateOrderDTO.
     *
     * @author Arthur Amorim
     *
     * @param  product - Entidade Product a ser convertida em ProductCreateOrderDTO.
     *
     * @return ProductCreateOrderDTO convertido
     *
     * */
    @Override
    public ProductCreateOrderDTO convert(Product product) {
        this.id = product.getId();
        this.quantity = product.getQuantity();
        return null;
    }
}
