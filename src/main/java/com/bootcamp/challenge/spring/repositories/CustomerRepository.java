package com.bootcamp.challenge.spring.repositories;

import com.bootcamp.challenge.spring.entities.Customer;
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
public class CustomerRepository implements Repository<Customer> {

    private List<Customer> customers;

    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private final String PATH = "customers.json";

    public CustomerRepository() throws IOException {
        customers = new ArrayList<>();
        try  {
            loadShoppingCart();
        } catch (FileNotFoundException e) {
            saveFile();
        }
    }

    @Override
    public Customer create(Customer customer) throws IOException {
        customer.setId(System.currentTimeMillis());
        customers.add(customer);
        saveFile();
        return customer;
    }

    @Override
    public void delete(Customer customer) throws IOException {
        customers.remove(customer);
        saveFile();
    }

    @Override
    public void update(Customer customer) throws IOException {
        Customer customerToRemove = customers.stream()
                .filter(customerInRepo -> customerInRepo.getId().equals(customer.getId())).findFirst().get();
        customers.remove(customerToRemove);
        customers.add(customer);
        saveFile();
    }

    @Override
    public List<Customer> list() {
        return this.customers;
    }

    private void saveFile() throws IOException {
        objectMapper.writeValue(new File(PATH), customers);
    }

    private void loadShoppingCart() throws IOException {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        List<Customer> customerInFile = Arrays.asList(objectMapper.readValue(fileInputStream, Customer[].class));
        customers.addAll(customerInFile);
    }
}
