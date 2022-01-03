package com.bootcamp.challenge.spring.repositories;


import com.bootcamp.challenge.spring.entities.ShoppingCart;
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

/**Classe para realziar a persistencia e a leitura de registros no arquivo de armazenamento "shopping-cart.json"
 *
 *
 * @author Arthur Amorim
 * @author Jefferson Moreira
 * @author Samuel Stalschus
 *
 * */

@org.springframework.stereotype.Repository
public class ShoppingCartRepository implements Repository<ShoppingCart> {

    /**
     * Lista de carrinhos de compra armazenadas em memoria
    * */
    private List<ShoppingCart> shoppingCarts;
    /**
     * Objeto utilziado para realziar a serialização dos objetos e fazer a leitura e persistencia dos carrinhos de compras no arquivo json
     * */
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    /**
     * Path do arquivo que sera utilizado para armazenar os carrinhos de compras
     * */
    private final String PATH = "shopping-cart.json";


    /**
     * Construtor que inicializa o repositorio,
     * ele valida se precisa carregar os dados ja existentes ou criar um arquivo novo
     * */
    public ShoppingCartRepository() throws IOException {
        shoppingCarts = new ArrayList<>();
        try  {
            loadShoppingCart();
        } catch (FileNotFoundException e) {
            saveFile();
        }
    }

    /**
     * Adiciona uma ordem a lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param shoppingCart - Pedido para ser adicionado a lista e ao arquivo
     * */
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) throws IOException {
        shoppingCart.setId(System.currentTimeMillis());
        shoppingCarts.add(shoppingCart);
        saveFile();
        return shoppingCart;
    }

    /**
     * Remove uma ordem a lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param shoppingCart - Pedido para ser removido da lista e do arquivo
     * */
    @Override
    public void delete(ShoppingCart shoppingCart) throws IOException {
        shoppingCarts.remove(shoppingCart);
        saveFile();
    }

    /**
     * Atualiza uma ordem da lista de ordens e realiza a persistencia da nova lista no arquivo
     *
     * @param shoppingCart - Pedido já atualziado e pronto para ser persistido
     * */
    @Override
    public void update(ShoppingCart shoppingCart) throws IOException {
        ShoppingCart shoppingCartToRemove = shoppingCarts.stream()
                .filter(shoppingCartInRepo -> shoppingCartInRepo.getId().equals(shoppingCart.getId())).findFirst().get();
        shoppingCarts.remove(shoppingCartToRemove);
        shoppingCarts.add(shoppingCart);
        saveFile();
    }

    /**
     * Retorna a lista de ordens do sistema
     *
     * @return List<ShoppingCart> - Lista de ordens do sistema
     * */
    @Override
    public List<ShoppingCart> list() {
        return this.shoppingCarts;
    }

    /**
     * Realiza a escrita da lista de pedidos no arquivo de armazenamento
     * Caso o arquivo nao exista, ele cria um arquivo novo
     **/
    private void saveFile() throws IOException {
        objectMapper.writeValue(new File(PATH), shoppingCarts);
    }

    /**
     * Realia a leitura dos registros armazenados no arquivo e adiciona na lista em memoria
     * */
    private void loadShoppingCart() throws IOException {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        List<ShoppingCart> shoppingCartsInFile = Arrays.asList(objectMapper.readValue(fileInputStream, ShoppingCart[].class));
        shoppingCarts.addAll(shoppingCartsInFile);
    }
}
