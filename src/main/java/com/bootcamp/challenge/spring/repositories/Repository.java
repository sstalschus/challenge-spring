package com.bootcamp.challenge.spring.repositories;

import java.util.List;

public interface Repository<T> {
    void create(T t);
    void delete(T t);
    void update(T t);
    List<T> list(T t);
}
