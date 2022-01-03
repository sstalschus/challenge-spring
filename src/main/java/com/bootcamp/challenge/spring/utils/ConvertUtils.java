package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.dtos.CustomerDTO;
import com.bootcamp.challenge.spring.dtos.OrderDTO;
import com.bootcamp.challenge.spring.dtos.ShoppingCartDTO;
import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.entities.Customer;
import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.entities.ShoppingCart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    public static List<ProductDTO> convertToProductDTO(List<Product> products) {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> productDTOS.add(new ProductDTO().convert(product)));
        return productDTOS;
    }

    public static List<Product> converteToProduct(List<ProductDTO> productDTOS) {
        ArrayList<Product> products = new ArrayList<>();
        productDTOS.forEach(productDTO -> products.add(productDTO.convert()));
        return products;
    }

    public static List<OrderDTO> converteToOrderDTO(List<Order> orders) {
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(new OrderDTO().convert(order)));
        return orderDTOS;
    }

    public static List<ShoppingCartDTO> converteToShoppingCartDTO(List<ShoppingCart> shoppingCarts) {
        ArrayList<ShoppingCartDTO> shoppingCartDTOS = new ArrayList<>();
        shoppingCarts.forEach(shoppingCart -> shoppingCartDTOS.add(new ShoppingCartDTO().convert(shoppingCart)));
        return shoppingCartDTOS;
    }
}
