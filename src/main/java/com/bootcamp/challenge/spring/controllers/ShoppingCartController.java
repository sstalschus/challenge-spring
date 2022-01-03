package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.ShoppingCartDTO;
import com.bootcamp.challenge.spring.entities.ShoppingCart;
import com.bootcamp.challenge.spring.services.ShoppingCartService;
import com.bootcamp.challenge.spring.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Entpoint para listar todos os carrinhos de compra.
     *
     * @return ResponseEntity<List<ShoppingCartDTO>> - Retorna a lista de carrinhos que foram criados
     * */
    @GetMapping("")
    public ResponseEntity<List<ShoppingCartDTO>> get() {
        List<ShoppingCartDTO> shoppingCartDTOS = ConvertUtils.converteToShoppingCartDTO(shoppingCartService.getAllShoppingCart());
        return ResponseEntity.ok(shoppingCartDTOS);
    }

}
