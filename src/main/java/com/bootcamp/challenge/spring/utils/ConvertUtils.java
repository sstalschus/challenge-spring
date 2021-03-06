package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.dtos.OrderDTO;
import com.bootcamp.challenge.spring.dtos.ShoppingCartDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.entities.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

/**Classe com metodos estaticos para auxiliar na conversao de lista de objetos em DTOs e lista de DTOs e objetos
 * @author Arthur Amorim
 * */
public class ConvertUtils {

    /** Método usado para converter uma lista de Product em ProductDTO.
     *
     * @author Arthur Amorim
     *
     * @param  products - Lista de entidades para converter.
     *
     * @return lista convertida
     *
     * */
    public static List<ProductDTO> convertToProductDTO(List<Product> products) {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> productDTOS.add(new ProductDTO().convert(product)));
        return productDTOS;
    }

    /** Método usado para converter uma lista de ProductDTO em Product.
     *
     * @author Arthur Amorim
     *
     * @param  productDTOS - Lista de entidades para converter.
     *
     * @return lista convertida
     *
     * */
    public static List<Product> converteToProduct(List<ProductDTO> productDTOS) {
        ArrayList<Product> products = new ArrayList<>();
        productDTOS.forEach(productDTO -> products.add(productDTO.convert()));
        return products;
    }


    /** Método usado para converter uma lista de Order em OrderDTO.
            *
            * @author Arthur Amorim
     *
             * @param  orders - Lista de entidades para converter.
     *
             * @return lista convertida
     *
             * */
    public static List<OrderDTO> converteToOrderDTO(List<Order> orders) {
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(new OrderDTO().convert(order)));
        return orderDTOS;
    }

    /** Método usado para converter uma lista de ShoppingCart em ShoppingCartDTO.
     *
     * @author Arthur Amorim
     *
     * @param  shoppingCarts - Lista de entidades para converter.
     *
     * @return lista convertida
     *
     * */
    public static List<ShoppingCartDTO> converteToShoppingCartDTO(List<ShoppingCart> shoppingCarts) {
        ArrayList<ShoppingCartDTO> shoppingCartDTOS = new ArrayList<>();
        shoppingCarts.forEach(shoppingCart -> shoppingCartDTOS.add(new ShoppingCartDTO().convert(shoppingCart)));
        return shoppingCartDTOS;
    }
}
