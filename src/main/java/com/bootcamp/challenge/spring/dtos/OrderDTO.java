package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO implements ConvertDTO<Order, OrderDTO> {

    private List<Product> productList;
    private BigDecimal totalValue;

    @Override
    public Order convert() {
        return new Order(this.productList);
    }

    @Override
    public OrderDTO convert(Order order) {
        this.productList = order.getProductList();
        this.totalValue = order.getTotalValue();
        return this;
    }
}
