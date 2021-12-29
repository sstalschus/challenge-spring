package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> create(Product product){
        productService.createProduct(product);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/")
    public String update(){
        return "Patch";
    }

    @DeleteMapping("/")
    public String delete(){

        return "Delete";
    }
}


