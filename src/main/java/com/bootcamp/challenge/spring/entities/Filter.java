package com.bootcamp.challenge.spring.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Filter {

    private String category;
    private Boolean freeShiping;
    private String productName;
    private String brandName;
    private Integer order;


    public boolean hasFilter() {
        return category != null || freeShiping != null || productName != null ||  brandName != null || order != null;
    }
}
