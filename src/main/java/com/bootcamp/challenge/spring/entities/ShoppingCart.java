package com.bootcamp.challenge.spring.entities;

import com.bootcamp.challenge.spring.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    private Long id;
    private List<Order> orders = new ArrayList<>();


    public Double getTotal() {
        Double total = orders.stream().mapToDouble(order -> order.getTotalValue().doubleValue()).sum();
        return total;
    }
}
