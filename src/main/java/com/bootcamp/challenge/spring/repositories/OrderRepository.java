package com.bootcamp.challenge.spring.repositories;


import com.bootcamp.challenge.spring.entities.Order;
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

/**Classe para realziar a persistencia e a leitura de registros no arquivo de armazenamento "orders.json"
 *
 *
 * @author Arthur Amorim
 * @author Jefferson Moreira
 * @author Samuel Stalschus
 *
 * */

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<Order> {

    /**
     * Lista de ordens armazenadas em memoria
    * */
    private List<Order> orders;
    /**
     * Objeto utilziado para realziar a serialização dos objetos e fazer a leitura e persistencia dos pedidos no arquivo json
     * */
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /**
     * Path do arquivo que sera utilizado para armazenar os pedidos
     * */
    private final String PATH = "orders.json";


    /**
     * Construtor que inicializa o repositorio,
     * ele valida se precisa carregar os dados ja existentes ou criar um arquivo novo
     * */
    public OrderRepository() throws IOException {
        orders = new ArrayList<>();
        try  {
            loadOrderList();
        } catch (FileNotFoundException e) {
            saveFile();
        }
    }

    /**
     * Adiciona uma ordem a lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param order - Pedido para ser adicionado a lista e ao arquivo
     * */
    @Override
    public Order create(Order order) throws IOException {
        order.setId(System.currentTimeMillis());
        orders.add(order);
        saveFile();
        return order;
    }

    /**
     * Remove uma ordem a lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param order - Pedido para ser removido da lista e do arquivo
     * */
    @Override
    public void delete(Order order) throws IOException {
        orders.remove(order);
        saveFile();
    }

    /**
     * Atualiza uma ordem da lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param order - Pedido já atualziado e pronto para ser persistido
     * */
    @Override
    public void update(Order order) throws IOException {
        saveFile();
    }

    /**
     * Retorna a lista de ordens do sistema
     *
     * @return List<Order> - Lista de ordens do sistema
     * */
    @Override
    public List<Order> list() {
        return this.orders;
    }

    /**
     * Realiza a escrita da lista de pedidos no arquivo de armazenamento
     * Caso o arquivo nao exista, ele cria um arquivo novo
     **/
    private void saveFile() throws IOException {
        objectMapper.writeValue(new File(PATH), orders);
    }

    /**
     * Realia a leitura dos registros armazenados no arquivo e adiciona na lista em memoria
     * */
    private void loadOrderList() throws IOException {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        List<Order> ordersInFile = Arrays.asList(objectMapper.readValue(fileInputStream, Order[].class));
        orders.addAll(ordersInFile);
    }
}
