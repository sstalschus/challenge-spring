package com.bootcamp.challenge.spring.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;

/** Entidade Filtro
 *
 * @author Arthur Amorim
 * */
@Getter
@AllArgsConstructor
public class Filter {

    private String category;
    private Boolean freeShiping;
    private String productName;
    private String brandName;
    private Integer order;

    /** Verifica se existe algum valor para filtros na entidade
     *
     * @author Arthur Amorim
     *
     * @return Boolean
     *
     * */
    public boolean hasFilter() {
        return category != null || freeShiping != null || productName != null ||  brandName != null || order != null;
    }
}
