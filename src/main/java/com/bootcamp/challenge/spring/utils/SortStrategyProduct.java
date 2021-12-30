package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.entities.Product;

import java.util.Comparator;
import java.util.List;

public enum SortStrategyProduct {

    ALPHABETICAL_ASC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getName));
            return products;
        }
    },
    ALPHABETICAL_DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getName).reversed());
            return products;
        }
    },
    PRICE_DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getPrice).reversed());
            return products;
        }
    },
    PRICE_ASC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getPrice));
            return products;
        }
    };


    public List<Product> sort(List<Product> products) {
        return products;
    }
}
