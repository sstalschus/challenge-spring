package com.bootcamp.challenge.spring.repositories;


import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.Product;
import com.bootcamp.challenge.spring.repositories.interfaces.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**Classe para realziar a persistencia e a leitura de registros no arquivo de armazenamento "orders.json"
 *
 *
 * @author Arthur Amorim
 * @author Jefferson Moreira
 * @author Samuel Stalschus
 *
 * */
@org.springframework.stereotype.Repository
public class ProductRepository implements Repository<Product> {
    /**
     * Lista de produtos armazenadas em memoria
     * */
    private List<Product> products = new ArrayList<>();
    /**
     * Objeto utilziado para realziar a serialização dos objetos e fazer a leitura e persistencia dos pedidos no arquivo json
     * */
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /**
     * Path do arquivo que sera utilizado para armazenar os pedidos
     * */
    private final String PATH = "products.json";

    /**
     * Construtor que inicializa o repositorio,
     * ele valida se precisa carregar os dados ja existentes ou criar um arquivo novo
     * */
    public ProductRepository() throws IOException {
        try {
            loadOrderList();
        } catch (FileNotFoundException e) {
            saveFile();
        }
    }

    /**
     * Adiciona uma ordem a lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param product - Produto para ser adicionado a lista e ao arquivo
     * */
    @Override
    public Product create(Product product) throws IOException {
        product.setId(System.currentTimeMillis());
        this.products.add(product);
        saveFile();
        return product;
    }

    /**
     * Remove uma ordem a lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param product - Pedido para ser removido da lista e do arquivo
     * */
    @Override
    public void delete(Product product) throws IOException {
        products.remove(product);
        saveFile();
    }

    /**
     * Atualiza uma ordem da lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param product - Product já atualziado e pronto para ser persistido
     * */
    @Override
    public void update(Product product) throws IOException {
        for (Product element: this.products) {
            if(product.equals(element)) {
                if(product.getQuantity() != null) element.setQuantity(product.getQuantity());
                if(product.getPrice() != null) element.setPrice(product.getPrice());
                if(product.getCategory() != null) element.setCategory(product.getCategory());
                if(product.getBrand() != null) element.setBrand(product.getBrand());
                if(product.getName() != null) element.setName(product.getName());
                if(product.getPrestige() != null) element.setPrestige(product.getPrestige());
                if(product.getFreeshiping() != null) element.setFreeshiping(product.getFreeshiping());
            }
        }
        saveFile();
    }

    /**
     * Retorna a lista de ordens do sistema
     *
     * @return List<Product> - Lista de produtos do sistema
     * */
    @Override
    public List<Product> list() {
        return this.products;
    }

    /**
     * Realiza a escrita da lista de produtos no arquivo de armazenamento
     * Caso o arquivo nao exista, ele cria um arquivo novo
     **/
    private void saveFile() throws IOException {
        objectMapper.writeValue(new File(PATH), products);
    }

    /**
     * Realia a leitura dos registros armazenados no arquivo e adiciona na lista em memoria
     * */
    private void loadOrderList() throws IOException {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        List<Product> productsInFile = Arrays.asList(objectMapper.readValue(fileInputStream, Product[].class));
        products.addAll(productsInFile);
    }
}
