package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.FilterDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.entities.ShoppingCart;
import com.bootcamp.challenge.spring.services.ShoppingCartService;
import com.bootcamp.challenge.spring.utils.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("")
    public ResponseEntity<List<ShoppingCart>> get() throws IllegalAccessException {
        List<ShoppingCart> allShoppingCart = shoppingCartService.getAllShoppingCart();
        return ResponseEntity.ok(allShoppingCart);
    }

}
