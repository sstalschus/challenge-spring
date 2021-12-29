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
    public void create(Product product) throws IOException {
        product.setId(System.currentTimeMillis());
        this.products.add(product);
        saveFile();
    }

    @Override
    public void delete(Product product) throws IOException {
        products.remove(product);
        saveFile();
    }

    @Override
    public void update(Product product) throws IOException {
        saveFile();
    }

    @Override
    public List<Product> list(Product product) {
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
