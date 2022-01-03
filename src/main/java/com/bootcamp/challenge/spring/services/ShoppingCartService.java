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

    public ShoppingCart getShoppingCart(Long id) throws IOException {
        if (id != null) {
            //consulta no repository e retorna o carrinho existente
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


    private ShoppingCart create() throws IOException {
        return shoppingCartRepository.create(new ShoppingCart());
    }

    public void addOrder(Order order, ShoppingCart shoppingCart) throws IOException {
        shoppingCart.getOrders().add(order);
        shoppingCartRepository.update(shoppingCart);
    }

    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.list();
    }
}
