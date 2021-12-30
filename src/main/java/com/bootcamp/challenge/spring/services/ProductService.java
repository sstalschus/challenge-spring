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

/** Service de produtos
 *
 * @author Samuel Stalschus
 * @author Arthur Amorim
 * @author Lorraine Mendes
 * @author Jefferson Froes
 *
 * */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /** Método usado para criar um novo produto.
     *
     * @author Samuel Stalschus
     * @author Arthur Amorim
     * @author Lorraine Mendes
     * @author Jefferson Froes
     *
     * @param  product - Produto enviado pelo Client.
     *
     * @return Produto criado
     *
     * @throws RepositoryException - Exceção retornada quando ocorre qualquer erro de criação de produto que seja derivado de um IOExeption proveniente do repository.
     *
     * */
    public Product createProduct(Product product){
        if (!productValid(product)) throw new IllegalProductAtributesException("Obligatory fields not found");
        try{
            return productRepository.create(product);
        } catch (IOException e) {
            throw new RepositoryException("Fail to create a new Product \n\n\n\n" + e.getMessage());
        }
    }

    /** Método usado para listar todos os produtos.
     *
     * @author Jefferson Froes
     *
     * @return Lista de produtos
     *
     * */
    public List<Product> listAllProducts(){
        return productRepository.list();
    }

    /** Método usado para atualizar dados de um produto.
     *
     * @author Samuel Stalschus
     * @author Arthur Amorim
     *
     * @param  product - Produto enviado pelo Client.
     *
     * @throws RepositoryException - Exceção retornada quando ocorre qualquer erro de atualização de produto que seja derivado de um IOExeption proveniente do repository.
     *
     * */
    public void updateProduct(Product product) {
        try {
            productRepository.update(product);
        } catch (IOException e) {
            throw new RepositoryException("Fail to update a Product \n\n\n\n" + e.getMessage());
        }
    }

    /** Método usado para obter a lista de produtos com base nos filtros.
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     *
     * @return Lista de produtos
     *
     * @throws IllegalAccessException
     *
     * */
    public List<Product> getList(Filter filter) throws IllegalAccessException {

        if (!filter.hasFilter()) {
            return productRepository.list().stream()
                    .filter(product -> product.getQuantity() > 0).collect(Collectors.toList());
        }
        Set<Product> products = filterProductList(filter);
        List<Product> finalProducts = orderByTypeOrder(filter, products);

        return finalProducts;
    }

    /** Método usado como uma pipeline de filtros, onde cada filtro pode cair em um método filho.
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     *
     * @return Lista de produtos já filtrada
     *
     * */
    private Set<Product> filterProductList(Filter filter) {
        Set<Product> products = productRepository.list().stream()
                .filter(product -> product.getQuantity() > 0).collect(Collectors.toSet());
        products = filterByCategory(filter, products);
        products = filterByFreeShiping(filter, products);
        products = filterByProductName(filter, products);
        products = filterByBrandName(filter, products);
        return products;
    }

    /** Método usado para ordenar a lista com base no valor do order (Alfabético ou por preço).
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     * @param  products - Set criado para que os produtos não se repitam.
     *
     * @return Lista de produtos
     *
     * @throws IllegalAccessException
     *
     * */
    private List<Product> orderByTypeOrder(Filter filter, Set<Product> products) throws IllegalAccessException {
        List<Product> finalProducts = new ArrayList<>(products);
        if (filter.getOrder() != null) {
            SortType sortType = SortType.valueOf(filter.getOrder());
            finalProducts = SortStrategyProduct.valueOf(sortType.name()).sort(finalProducts);
        }
        return finalProducts;
    }

    /** Método usado fazer a filragem na lista pelo nome da marca.
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     * @param  products - Set criado para que os produtos não se repitam.
     *
     * @return Lista de produtos
     *
     * */
    private Set<Product> filterByBrandName(Filter filter, Set<Product> products) {
        if (filter.getBrandName() != null) {
            products = products.stream().filter(product -> product.getBrand().equals(filter.getBrandName())).collect(Collectors.toSet());
        }
        return products;
    }

    /** Método usado fazer a filragem na lista pelo nome do produto.
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     * @param  products - Set criado para que os produtos não se repitam.
     *
     * @return Lista de produtos
     *
     * */
    private Set<Product> filterByProductName(Filter filter, Set<Product> products) {
        if (filter.getProductName() != null) {
            products = products.stream().filter(product -> product.getName().equals(filter.getProductName())).collect(Collectors.toSet());
        }
        return products;
    }

    /** Método usado fazer a filragem na lista pelo frete gratis.
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     * @param  products - Set criado para que os produtos não se repitam.
     *
     * @return Lista de produtos
     *
     * */
    private Set<Product> filterByFreeShiping(Filter filter, Set<Product> products) {
        if (filter.getFreeShiping() != null) {
            products = products.stream().filter(product -> product.getFreeshiping() == filter.getFreeShiping()).collect(Collectors.toSet());
        }
        return products;
    }

    /** Método usado fazer a filragem na lista por categoria.
     *
     * @author Arthur Amorim
     *
     * @param  filter - Filtros enviados pelo cliente.
     * @param  products - Set criado para que os produtos não se repitam.
     *
     * @return Lista de produtos
     *
     * */
    private Set<Product> filterByCategory(Filter filter, Set<Product> products) {
        if (filter.getCategory() != null) {
            products = products.stream().filter(product -> {
                return product.getCategory().equals(filter.getCategory());
            }).collect(Collectors.toSet());
        }
        return products;
    }

    /** Método usado para filtrar por estoque positivo.
     *
     * @author Arthur Amorim
     *
     * @param  products - Set criado para que os produtos não se repitam.
     *
     * @return Lista de produtos
     *
     * */
    private Set<Product> filterByPositiveStock(Set<Product> products) {
        return products.stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toSet());
    }

    /** Método usado para verificar se o produto possui todos os campos criados.
     *
     * @author Arthur Amorim
     *
     * @param  product - Produto.
     *
     * @return Boolean com a informação
     *
     * */
    public boolean productValid(Product product) {
        return product.getName() != null &&
                product.getCategory() != null &&
                product.getBrand() != null &&
                product.getPrice() != null &&
                product.getQuantity() != null &&
                product.getFreeshiping() != null;
    }

}
