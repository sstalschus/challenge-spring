package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.FilterDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductCreateDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductUpdateDTO;

import com.bootcamp.challenge.spring.services.ProductService;
import com.bootcamp.challenge.spring.utils.ConvertUtils;
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

/**Controladora para receber requisições HTTP relacionadas a produtos
 * @author Daniel Ramos
 * @author Arthur Amorim
 * @author Lorraine Mendes
 * @author Samuel Stalschus
 * @author Jefferson Moreira
 * */

@RestController
@RequestMapping("/articles")
public class ProductController {

    /**Injeção de dependencia do Serviço de Pedidos
     * */
    @Autowired
    private ProductService productService;

    /**
     * Entpoint para o cadastro de um novo pedido com produtos.
     *
     * @param productCreateDTO - Lista contendo ID e Quantidade de Cada produto referente a ordem que sera criada
     *
     * @return ResponseEntity<ProductCreateDTO> - Retorna o produto que foi cadastrado
     * */
    @PostMapping("")
    public ResponseEntity<ProductCreateDTO> create(@RequestBody ProductCreateDTO productCreateDTO){
        ProductCreateDTO productCreated = new ProductCreateDTO().convert(productService.createProduct(productCreateDTO.convert()));
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    /**
     * Entpoint para consultar a lista de produtos.
     * É possivel realizar a pesquisa aplicando filtros, caso nao seja passado
     * nenhum filtro a busca retorna todos os protudos cadastrados.
     *
     * @param category - Filtra por determinada categoria de produto.
     * @param freeShiping - Filtra por produtos com envio gratis ou nao.
     * @param product - Filtra pelo nome do produto.
     * @param brand - Filtra pela marca do produtos.
     * @param order - Valor inteiro que define qual odernação deve ser aplicada segundo o SortType.java
     *
     *
     * @return ResponseEntity<List<ProductDTO>> - Retorna a lista de produtos do sistemas com ou sem aplicacap de filtros.
     * */
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> get(@Nullable @RequestParam String category,
                                                @Nullable @RequestParam Boolean freeShiping,
                                                @Nullable @RequestParam String product,
                                                @Nullable @RequestParam String brand,
                                                @Nullable @RequestParam Integer order) throws IllegalAccessException {
        FilterDTO filter = new FilterDTO(category, freeShiping, product, brand, order);
        return ResponseEntity.ok(ConvertUtils.convertToProductDTO(productService.getList(filter.convert())));
    }

    /**
     * Endpoitn para atualizar os dados de um produto
     *
     * @param productUpdateDTO - DTO que recebe quais campos podem ser editados de um produto
     *
     * @return String - retorna mensagem de status da operação.
     * */
    @PatchMapping("/")
    public ResponseEntity<String> update(@RequestBody ProductUpdateDTO productUpdateDTO){
        productService.updateProduct(productUpdateDTO.convert());
        return ResponseEntity.status(204).body("Atualização realizada com sucesso.");
    }

}
