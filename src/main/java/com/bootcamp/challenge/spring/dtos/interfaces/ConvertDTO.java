package com.bootcamp.challenge.spring.dtos.interfaces;

public interface ConvertDTO<T, D> {
    T convert();
    D convert(T t);
}
