package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO implements ConvertDTO<Order, OrderDTO> {

    private List<Product> productList;

    @Override
    public Order convert() {
        return new Order(this.productList);
    }

    @Override
    public OrderDTO convert(Order order) {
        this.productList = order.getProductList();
        return this;
    }

    public Double getTotal() {
        return productList.stream()
                .mapToDouble(product -> product.getPrice().doubleValue() * product.getQuantity()).sum();
    }
}
