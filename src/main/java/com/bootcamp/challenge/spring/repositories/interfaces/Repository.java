package com.bootcamp.challenge.spring.repositories.interfaces;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    T create(T t) throws IOException;
    void delete(T t) throws IOException;
    void update(T t) throws IOException;
    List<T> list();
}
