package com.bootcamp.challenge.spring.repositories;


import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.repositories.interfaces.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Repository
public class ProductRepository implements Repository<Product> {

    private List<Product> products = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "products.json";

    public ProductRepository() throws IOException {
        try {
            loadOrderList();
        } catch (FileNotFoundException e) {
            saveFile();
        }
    }

    @Override
    public Product create(Product product) throws IOException {
        product.setId(System.currentTimeMillis());
        this.products.add(product);
        saveFile();
        return product;
    }

    @Override
    public void delete(Product product) throws IOException {
        products.remove(product);
        saveFile();
    }

    @Override
    public void update(Product product) throws IOException {
        for (Product element: this.products) {
            if(product.equals(element)) {
                if(product.getQuantity() != null) element.setQuantity(product.getQuantity());
                if(product.getPrice() != null) element.setPrice(product.getPrice());
                if(product.getCategory() != null) element.setCategory(product.getCategory());
                if(product.getBrand() != null) element.setBrand(product.getBrand());
                if(product.getName() != null) element.setName(product.getName());
                if(product.getPrestige() != null) element.setPrestige(product.getPrestige());
                if(product.getFreeshiping() != null) element.setFreeshiping(product.getFreeshiping());
            }
        }
        saveFile();
    }

    @Override
    public List<Product> list() {
        return this.products;
    }

    private void saveFile() throws IOException {
        objectMapper.writeValue(new File(PATH), products);
    }

    private void loadOrderList() throws IOException {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        List<Product> productsInFile = Arrays.asList(objectMapper.readValue(fileInputStream, Product[].class));
        products.addAll(productsInFile);
    }
}
