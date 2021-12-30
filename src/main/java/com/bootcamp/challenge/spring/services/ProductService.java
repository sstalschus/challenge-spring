package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.enums.SortType;
import com.bootcamp.challenge.spring.repositories.ProductRepository;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product){
        try{
            productRepository.create(product);
        } catch (IOException e) {
            throw new RepositoryException("Fail to create a new Product \n\n\n\n" + e.getMessage());
        }
    }

    public List<Product> listAllProducts(){
        return productRepository.list();
    }

    public void updateProductQuantity(Product product) {
        try {
            productRepository.update(product);
        } catch (IOException e) {
            throw new RepositoryException("Fail to update a Product \n\n\n\n" + e.getMessage());
        }
    }
}
