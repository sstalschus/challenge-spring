package com.bootcamp.challenge.spring.dtos.interfaces;


/**
 * Interface para realizar a conversao entre DTO e Entidade
 *
 * @param <T, D> - Tipo da Entidade e DTO que serao convertidos
 * */
public interface ConvertDTO<T, D> {
    T convert();
    D convert(T t);
}
