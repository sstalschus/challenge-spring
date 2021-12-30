package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.dtos.products.ProductDTO;
import com.bootcamp.challenge.spring.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductUtil {

    public static List<ProductDTO> convertToProductDTO(List<Product> products) {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> productDTOS.add(new ProductDTO().convert(product)));
        return productDTOS;
    }
}
