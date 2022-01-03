package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/** DTO de pedido
 *
 * @author Arthur Amorim
 *
 * */
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO implements ConvertDTO<Order, OrderDTO> {

    private List<Product> productList;
    private BigDecimal totalValue;

    /** Método usado para converter a entidade em pedido.
     *
     * @author Arthur Amorim
     *
     * @return Ordem convertida
     *
     * */
    @Override
    public Order convert() {
        return new Order(this.productList);
    }

    /** Método usado para converter a entidade pedido em pedidoDTO .
     *
     * @author Arthur Amorim
     *
     * @param  order - Entidade Pedido a ser convertida em PedidoDTO.
     *
     * @return PEdido convertido
     *
     * */
    @Override
    public OrderDTO convert(Order order) {
        this.productList = order.getProductList();
        this.totalValue = order.getTotalValue();
        return this;
    }
}
