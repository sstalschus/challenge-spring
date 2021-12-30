package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.FilterDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductCreateDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductUpdateDTO;

import com.bootcamp.challenge.spring.services.ProductService;
import com.bootcamp.challenge.spring.utils.ProductUtil;
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
    public ResponseEntity<ProductCreateDTO> create(@RequestBody ProductCreateDTO productCreateDTO){
        ProductCreateDTO productCreated = new ProductCreateDTO().convert(productService.createProduct(productCreateDTO.convert()));
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> get(@Nullable @RequestParam String category, @Nullable @RequestParam Boolean freeShiping, @Nullable @RequestParam String product, @Nullable @RequestParam String brand, @Nullable @RequestParam Integer order) throws IllegalAccessException {
        FilterDTO filter = new FilterDTO(category, freeShiping, product, brand, order);
        return ResponseEntity.ok(ProductUtil.convertToProductDTO(productService.getList(filter.convert())));
    }

    @PatchMapping("/")
    public ResponseEntity<String> update(@RequestBody ProductUpdateDTO productUpdateDTO){
        productService.updateProduct(productUpdateDTO.convert());
        return ResponseEntity.status(204).body("Atualização realizada com sucesso.");
    }

}
