package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ProductController {

    @GetMapping("")
    public String get(@Nullable @RequestParam String category, @Nullable @RequestParam Boolean freeShiping, @Nullable @RequestParam String product, @Nullable @RequestParam String brand, @Nullable @RequestParam Integer order) {
        return "Parametros: order: "+ order + " category: " + category + " freeShiping: " + freeShiping + " product: " + product + " brand: " + brand;
    }
}
