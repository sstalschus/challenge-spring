package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Filter;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.enums.SortType;
import com.bootcamp.challenge.spring.repositories.ProductRepository;
import com.bootcamp.challenge.spring.shared.exceptions.IllegalProductAtributesException;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import com.bootcamp.challenge.spring.utils.SortStrategyProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        if (!productValid(product)) throw new IllegalProductAtributesException("Obligatory fields not found");
        try{
            return productRepository.create(product);
        } catch (IOException e) {
            throw new RepositoryException("Fail to create a new Product \n\n\n\n" + e.getMessage());
        }
    }

    public List<Product> listAllProducts(){
        return productRepository.list();
    }

    public void updateProduct(Product product) {
        try {
            productRepository.update(product);
        } catch (IOException e) {
            throw new RepositoryException("Fail to update a Product \n\n\n\n" + e.getMessage());
        }
    }

    public List<Product> getList(Filter filter) throws IllegalAccessException {

        if (!filter.hasFilter()) {
            return productRepository.list();
        }
        Set<Product> products = filterProductList(filter);
        List<Product> finalProducts = orderByTypeOrder(filter, products);

        return finalProducts;
    }

    private Set<Product> filterProductList(Filter filter) {
        Set<Product> products = new HashSet<>(productRepository.list());
        products = filterByCategory(filter, products);
        products = filterByFreeShiping(filter, products);
        products = filterByProductName(filter, products);
        products = filterByBrandName(filter, products);
        return products;
    }

    private List<Product> orderByTypeOrder(Filter filter, Set<Product> products) throws IllegalAccessException {
        List<Product> finalProducts = new ArrayList<>(products);
        if (filter.getOrder() != null) {
            SortType sortType = SortType.valueOf(filter.getOrder());
            finalProducts = SortStrategyProduct.valueOf(sortType.name()).sort(finalProducts);
        }
        return finalProducts;
    }

    private Set<Product> filterByBrandName(Filter filter, Set<Product> products) {
        if (filter.getBrandName() != null) {
            products = products.stream().filter(product -> product.getBrand().equals(filter.getBrandName())).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByProductName(Filter filter, Set<Product> products) {
        if (filter.getProductName() != null) {
            products = products.stream().filter(product -> product.getName().equals(filter.getProductName())).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByFreeShiping(Filter filter, Set<Product> products) {
        if (filter.getFreeShiping() != null) {
            products = products.stream().filter(product -> product.getFreeshiping() == filter.getFreeShiping()).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByCategory(Filter filter, Set<Product> products) {
        if (filter.getCategory() != null) {
            products = products.stream().filter(product -> {
                return product.getCategory().equals(filter.getCategory());
            }).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByPositiveStock(Set<Product> products) {
        return products.stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toSet());
    }

    public boolean productValid(Product product) {
        return product.getName() != null &&
                product.getCategory() != null &&
                product.getBrand() != null &&
                product.getPrice() != null &&
                product.getQuantity() != null &&
                product.getFreeshiping() != null;
    }

}
