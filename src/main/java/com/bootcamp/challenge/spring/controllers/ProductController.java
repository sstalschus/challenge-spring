package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.entities.Filter;
import com.bootcamp.challenge.spring.entities.Product;

import com.bootcamp.challenge.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> create(@RequestBody Product product){
        productService.createProduct(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> get(@Nullable @RequestParam String category, @Nullable @RequestParam Boolean freeShiping, @Nullable @RequestParam String product, @Nullable @RequestParam String brand, @Nullable @RequestParam Integer order) throws IllegalAccessException {
        Filter filter = new Filter(category, freeShiping, product, brand, order);
        return ResponseEntity.ok(productService.getList(filter));
    }

    @PatchMapping("/")
    public ResponseEntity<String> update(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseEntity.status(204).body("Atualização realizada com sucesso.");
    }

}
