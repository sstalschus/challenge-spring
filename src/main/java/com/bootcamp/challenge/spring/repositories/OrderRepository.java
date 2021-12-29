package com.bootcamp.challenge.spring.repositories;


import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.repositories.interfaces.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<Order> {

    private List<Order> orders = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "orders.json";


    public OrderRepository() throws IOException {
        try  {
            loadOrderList();
        } catch (FileNotFoundException e) {
            saveFile();
        }
    }

    @Override
    public void create(Order order) throws IOException {
        order.setId(System.currentTimeMillis());
        orders.add(order);
        saveFile();
    }

    @Override
    public void delete(Order order) throws IOException {
        orders.remove(order);
        saveFile();
    }

    @Override
    public void update(Order order) throws IOException {
        saveFile();
    }

    @Override
    public List<Order> list(Order order) {
        return this.orders;
    }

    private void saveFile() throws IOException {
        objectMapper.writeValue(new File(PATH), orders);
    }

    private void loadOrderList() throws IOException {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        this.orders = Arrays.asList(objectMapper.readValue(fileInputStream, Order[].class));
    }
}
