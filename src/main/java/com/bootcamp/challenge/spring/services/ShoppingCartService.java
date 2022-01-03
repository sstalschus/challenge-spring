package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.entities.Order;
import com.bootcamp.challenge.spring.entities.ShoppingCart;
import com.bootcamp.challenge.spring.repositories.ShoppingCartRepository;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ShoppingCartService{

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**Busca um carrinho existente, ou solicita que um carrinho novo seja criado
     *
     * @param id - ID do carrinho( Caso venha nulo, solicita que um carrinho novo seja criado)
     *
     * @return ShoppingCart - Carrinho de Compras com ID.
     * */
    public ShoppingCart getShoppingCart(Long id) throws IOException {
        if (id != null) {
            ShoppingCart shoppingCartOnRepo = shoppingCartRepository.list().stream()
                    .filter(shoppingCart -> shoppingCart.getId().equals(id)).findFirst().get();
            if (shoppingCartOnRepo.equals(null)) {
                throw new RepositoryException("Shopping Cart not found.");
            }
            return shoppingCartOnRepo;
        } else {
            return create();
        }
    }


    /**Cria um carrinho novo
     *
     * @return ShoppingCart - Carrinho novo criado e com ID
     * */
    private ShoppingCart create() throws IOException {
        return shoppingCartRepository.create(new ShoppingCart());
    }

    /**Adiciona um pedido a um carrinho ja existente
     *
     * @param order - Pedido que sera adicionado ao carrinho
     * @param shoppingCart - Carrinho
     * */
    public void addOrder(Order order, ShoppingCart shoppingCart) throws IOException {
        shoppingCart.getOrders().add(order);
        shoppingCartRepository.update(shoppingCart);
    }

    /**Lista todos os carrinhos de compras
     *
     * @return List<ShoppingCart> -  Lista de todos os carrinhos existentes do sistema
     * */
    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.list();
    }
}
