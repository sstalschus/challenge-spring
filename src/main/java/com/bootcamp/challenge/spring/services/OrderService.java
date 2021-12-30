package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.repositories.OrderRepository;
import com.bootcamp.challenge.spring.shared.exceptions.ProductNotFoundException;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Service de pedidos
 *
 * @author Samuel Stalschus
 * @author Arthur Amorim
 * @author Daniel Ramos
 * */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    /** Método usado para criar um novo pedido.
     *
     * @author Samuel Stalschus
     * @author Arthur Amorim
     * @author Daniel Ramos
     *
     * @param  productList - Lista de produtos enviada pelo Client.
     *
     * @return Pedido criado
     *
     * @throws RepositoryException - Exceção retornada quando ocorre qualquer erro de criação de pedido que seja derivado de um IOExceptionproveniente do repository.
     * */
    public Order createOrder(List<Product> productList) {
        try {
            satinizeProductList(productList);
            verifyAndUpdateProducts(productList);
            Order order = new Order();
            order.setProductList(productList);
            double total = productList.stream().mapToDouble(product -> product.getPrice().doubleValue() * product.getQuantity()).sum();
            order.setTotalValue(BigDecimal.valueOf(total));
            order.getProductList().add(new Product());
            return orderRepository.create(order);
        } catch (IOException e) {
            throw new RepositoryException("Fail to create a new Order \n\n\n\n" + e.getMessage());
        }
    }

    /** Método usado para verificar se todos os produtos informados na lista existem,
     * se possuem quantidade disponível em estoque, e caso possua a quantidade é feito o update no estoque.
     *
     * @author Samuel Stalschus
     *
     * @param  userList - Lista de produtos enviada pelo Client.
     *
     * @throws ProductNotFoundException - Exceção retornada caso o produto não seja encontrado ou caso ele não possua quantidade suficiente
     * */
    private void verifyAndUpdateProducts(List<Product> userList) {
        List<Product> productsList = productService.listAllProducts();
        userList.forEach(userProduct -> {
            int count = 0;
            for (Product product: productsList) {
                if(product.equals(userProduct)) {
                    if(userProduct.getQuantity() > product.getQuantity()) throw new ProductNotFoundException("Product ID "+ userProduct.getId() +" not have quantity necessary");
                    userProduct.setName(product.getName());
                    userProduct.setBrand(product.getBrand());
                    userProduct.setPrice(product.getPrice());
                    userProduct.setCategory(product.getCategory());
                    userProduct.setFreeshiping(product.getFreeshiping());
                    userProduct.setPrestige(product.getPrestige());
                    product.setQuantity(product.getQuantity() - userProduct.getQuantity());
                    productService.updateProduct(product);
                    count++;
                }
            }
            if(count == 0) throw new ProductNotFoundException("Product ID "+ userProduct.getId() +" not found");
        });
    }

    /** Método usado para sanitizar os produtos, unificando as quantidades de um mesmo produto caso sejam enviadas separadamente
     *
     * @author Samuel Stalschus
     *
     * @param  products - Lista de produtos enviada pelo Client.
     * */
    private void satinizeProductList(List<Product> products) {
        List<Product> productSanitize = new ArrayList<>();
        products.forEach( product -> {
            if (Collections.frequency(productSanitize, product) >=1)
                productSanitize.get(productSanitize.indexOf(product))
                        .setQuantity(productSanitize.get(productSanitize.indexOf(product)).getQuantity() + product.getQuantity());
            else
                productSanitize.add(product);
        });
        products.clear();
        products.addAll(productSanitize);
    }
}
