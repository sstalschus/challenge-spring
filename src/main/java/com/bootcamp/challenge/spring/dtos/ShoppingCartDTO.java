package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.entities.ShoppingCart;
import com.bootcamp.challenge.spring.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartDTO implements ConvertDTO<ShoppingCart, ShoppingCartDTO> {

    private Long id;
    private List<OrderDTO> orders;


    /** Método usado para converter a entidade em ShoppingCart.
     *
     * @author Arthur Amorim
     *
     * @return Ordem convertida
     *
     * */
    @Override
    public ShoppingCart convert() {
        return null;
    }

    /** Método usado para converter a entidade ShoppingCart em ShoppingCartDTO .
     *
     * @author Arthur Amorim
     *
     * @param  shoppingCart - Entidade Carrinho de compra a ser convertida em ShoppingCartDTO.
     *
     * @return Entidade convertida para ShoppingCartDTO
     *
     * */
    @Override
    public ShoppingCartDTO convert(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.orders = ConvertUtils.converteToOrderDTO(shoppingCart.getOrders());
        return this;
    }
}
