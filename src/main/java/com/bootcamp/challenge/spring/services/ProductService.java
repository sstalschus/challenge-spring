package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.enums.SortType;
import com.bootcamp.challenge.spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product){

        try{
            productRepository.create(product);

        } catch (IOException exception){
            System.out.println(exception.getMessage());

        }




    }
}
