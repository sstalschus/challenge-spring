package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.OrderDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductCreateOrderDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchase-request")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<ProductCreateOrderDTO> productList){
        OrderDTO orderDTO = new OrderDTO().convert(
                orderService.createOrder(
                        productList.stream().<Product>map(product -> product.convert())
                                .collect(Collectors.toList())));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

}
