package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(List<Product> productList) throws IOException {
        Order order = new Order();
        order.setProductList(productList);
        double total = productList.stream().mapToDouble(product -> product.getPrice().doubleValue() * product.getQuantity()).sum();
        order.setTotalValue(BigDecimal.valueOf(total));
        orderRepository.create(order);
    }

}
