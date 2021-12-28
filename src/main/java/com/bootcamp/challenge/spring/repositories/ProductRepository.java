package com.bootcamp.challenge.spring.repositories;


import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.repositories.interfaces.Repository;

import java.util.List;

public class ProductRepository implements Repository<Product> {
    @Override
    public void create(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> list(Product product) {
        return null;
    }
}
