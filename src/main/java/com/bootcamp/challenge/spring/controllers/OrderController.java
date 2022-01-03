package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.OrderDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductCreateOrderDTO;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**Controladora para receber requisições HTTP relacionadas a ordem de compra
 * @author Daniel Ramos
 * @author Arthur Amorim
 * @author Lorraine Mendes
 * */
@RestController
@RequestMapping("/purchase-request")
public class OrderController {

    /**Injeção de dependencia do Serviço de Pedidos
     * */
    @Autowired
    private OrderService orderService;

    /**
     * Entpoint para o cadastro de um novo pedido com produtos.
     * @param productList - Lista contendo ID e Quantidade de Cada produto referente a ordem que sera criada
     * @return ResponseEntity<OrderDTO> - Retorna a Ordem de Compra
     * */
    @PostMapping(value = "")
    public ResponseEntity<OrderDTO> createOrder(@Nullable @RequestParam Long shoppingCartId, @RequestBody List<ProductCreateOrderDTO> productList){
        OrderDTO orderDTO = new OrderDTO().convert(
                orderService.createOrder(
                        productList.stream().<Product>map(product -> product.convert())
                                .collect(Collectors.toList()), shoppingCartId));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

}
