package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Filter;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.enums.SortType;
import com.bootcamp.challenge.spring.repositories.ProductRepository;
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

    public List<Product> getList(Filter filterDTO) throws IllegalAccessException {

        if (!filterDTO.hasFilter()) {
            return productRepository.list();
        }
        Set<Product> products = filterProductList(filterDTO);
        List<Product> finalProducts = orderByTypeOrder(filterDTO, products);

        return finalProducts;
    }

    private Set<Product> filterProductList(Filter filterDTO) {
        Set<Product> products = new HashSet<>(productRepository.list());
        products = filterByCategory(filterDTO, products);
        products = filterByFreeShiping(filterDTO, products);
        products = filterByProductName(filterDTO, products);
        products = filterByBrandName(filterDTO, products);
        return products;
    }

    private List<Product> orderByTypeOrder(Filter filterDTO, Set<Product> products) throws IllegalAccessException {
        List<Product> finalProducts = new ArrayList<>(products);
        if (filterDTO.getOrder() != null) {
            SortType sortType = SortType.valueOf(filterDTO.getOrder());
            finalProducts = SortStrategyProduct.valueOf(sortType.name()).sort(finalProducts);
        }
        return finalProducts;
    }

    private Set<Product> filterByBrandName(Filter filterDTO, Set<Product> products) {
        if (filterDTO.getBrandName() != null) {
            products = products.stream().filter(product -> product.getBrand().equals(filterDTO.getBrandName())).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByProductName(Filter filterDTO, Set<Product> products) {
        if (filterDTO.getProductName() != null) {
            products = products.stream().filter(product -> product.getName().equals(filterDTO.getProductName())).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByFreeShiping(Filter filterDTO, Set<Product> products) {
        if (filterDTO.getFreeShiping() != null) {
            products = products.stream().filter(product -> product.getFreeshiping() == filterDTO.getFreeShiping()).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByCategory(Filter filterDTO, Set<Product> products) {
        if (filterDTO.getCategory() != null) {
            products = products.stream().filter(product -> product.getCategory().equals(filterDTO.getCategory())).collect(Collectors.toSet());
        }
        return products;
    }

    private Set<Product> filterByPositiveStock(Set<Product> products) {
        return products.stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toSet());
    }


}
