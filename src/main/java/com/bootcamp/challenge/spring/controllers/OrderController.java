package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-request")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "")
    public ResponseEntity<Order> createOrder(@RequestBody List<Product> productList){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(productList));
    }
}
