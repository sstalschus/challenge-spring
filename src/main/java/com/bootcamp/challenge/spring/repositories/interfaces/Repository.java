package com.bootcamp.challenge.spring.repositories.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * Assinatura de metodos basicos para repositorios do sistema
 * Esta interface é generica para que possa ser criada inumeros repositorios
 *
 * @param <T> - Tipo do repositorio
 * */

public interface Repository<T> {
    T create(T t) throws IOException;
    void delete(T t) throws IOException;
    void update(T t) throws IOException;
    List<T> list();
}
