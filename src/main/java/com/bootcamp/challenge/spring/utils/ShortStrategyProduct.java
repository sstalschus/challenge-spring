package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.entities.Product;

import java.util.List;

public enum ShortStrategyProduct {

    ALPHABETICAL_ASC {
        @Override
        public List<Product> sort(List<Product> products) {
            System.out.println("Alphabetical asc Dentro do Strategy");
            return products;
        }
    },
    ALPHABETICAL_DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            System.out.println("Alphabetical desc Dentro do Strategy");
            return products;
        }
    },
    PRICE_DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            System.out.println("Price desc Dentro do Strategy");
            return products;
        }
    },
    PRICE_ASC {
        @Override
        public List<Product> sort(List<Product> products) {
            System.out.println("Price asc Dentro do Strategy");
            return products;
        }
    };


    public List<Product> sort(List<Product> products) {
        return products;
    }
}
