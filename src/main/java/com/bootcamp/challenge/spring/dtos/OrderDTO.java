package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.utils.ConvertUtils;
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

    private List<ProductDTO> productList;
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
        return new Order(ConvertUtils.converteToProduct(this.productList));
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
        this.productList = ConvertUtils.convertToProductDTO(order.getProductList());
        this.totalValue = order.getTotalValue();
        return this;
    }
}
