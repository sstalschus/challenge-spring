package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.entities.Product;

import java.util.List;

public enum ShorStrategy {

    DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            System.out.println("Dentro do Strategy");
            return products;
        }
    },
    ASC{
        @Override
        public List<Product> sort(List<Product> products) {
            System.out.println("ASC Dentro do Strategy");
            return products;
        }
    };




    public List<Product> sort(List<Product> products) {
        return products;
    }
}
