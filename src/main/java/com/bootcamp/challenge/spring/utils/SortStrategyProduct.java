package com.bootcamp.challenge.spring.utils;

import com.bootcamp.challenge.spring.entities.Product;

import java.util.Comparator;
import java.util.List;

/**Estrategia para realizar a ordenacao da lista de produtos de acordo com o Enum SortType
 * @author Arthur Amorim
 * @author Samuel Stalschus
 * */

public enum SortStrategyProduct {
    /**
     * Realiza a ordenacao pelo campo Name do Produto de forma ascendente
     *
     * @author Samuel Stalschus
     *
     * @param products - List de produtos não ordenada.
     *
     * @return List<Product> - Retorna a Lista ordenada de acordo com o typo de ordenação necessaria
     * */
    ALPHABETICAL_ASC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getName));
            return products;
        }
    },
    /**
     * Realiza a ordenação pelo campo Name do Produto de forma descendente
     *
     * @author Samuel Stalschus
     *
     * @param products - List de produtos não ordenada.
     *
     * @return List<Product> - Retorna a Lista ordenada de forma descendente
     * */
    ALPHABETICAL_DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getName).reversed());
            return products;
        }
    },
    /**
     * Realiza a ordenacao pelo campo Price do produto de forma decrescente
     *
     * @author Arthur Amorim
     *
     * @param products - List de produtos não ordenada.
     *
     * @return List<Product> - Retorna a Lista ordenada de forma decrescente
     * */
    PRICE_DESC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getPrice).reversed());
            return products;
        }
    },
    /**
     * Realiza a ordenacao pelo campo Price do produto de forma crescente
     *
     * @author Arthur Amorim
     *
     * @param products - List de produtos não ordenada.
     *
     * @return List<Product> - Retorna a Lista ordenada de forma crescente
     * */
    PRICE_ASC {
        @Override
        public List<Product> sort(List<Product> products) {
            products.sort(Comparator.comparing(Product::getPrice));
            return products;
        }
    };

    /**
     * Metodo base de ordenação
     * */
    public List<Product> sort(List<Product> products) {
        return products;
    }
}
