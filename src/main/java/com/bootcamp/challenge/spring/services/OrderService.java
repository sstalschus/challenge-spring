package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.repositories.OrderRepository;
import com.bootcamp.challenge.spring.shared.exceptions.ProductNotFoundException;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public Order createOrder(List<Product> productList) {
        try {
            verifyAndUpdateProducts(productList);
            Order order = new Order();
            order.setProductList(productList);
            double total = productList.stream().mapToDouble(product -> product.getPrice().doubleValue() * product.getQuantity()).sum();
            order.setTotalValue(BigDecimal.valueOf(total));
            return orderRepository.create(order);
        } catch (IOException e) {
            throw new RepositoryException("Fail to create a new Order \n\n\n\n" + e.getMessage());
        }
    }

    private void verifyAndUpdateProducts(List<Product> userList) {
        List<Product> productsList = productService.listAllProducts();

        userList.forEach(userProduct -> {
            int count = 0;
            for (Product product: productsList) {
                if(product.equals(userProduct)) {
                    if(userProduct.getQuantity() > product.getQuantity()) throw new ProductNotFoundException("Product ID "+ userProduct.getId() +" not have quantity necessary");
                    userProduct.setName(product.getName());
                    userProduct.setBrand(product.getBrand());
                    userProduct.setPrice(product.getPrice());
                    userProduct.setCategory(product.getCategory());
                    userProduct.setFreeshiping(product.getFreeshiping());
                    userProduct.setPrestige(product.getPrestige());
                    product.setQuantity(product.getQuantity() - userProduct.getQuantity());
                    productService.updateProductQuantity(product);
                    count++;
                }
            }
            if(count == 0) throw new ProductNotFoundException("Product ID "+ userProduct.getId() +" not found");
        });
    }
}
