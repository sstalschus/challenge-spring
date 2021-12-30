package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDTO implements ConvertDTO<Filter, FilterDTO> {

    private String category;
    private Boolean freeShiping;
    private String productName;
    private String brandName;
    private Integer order;

    protected FilterDTO(String category, Boolean freeShiping, String productName, String brandName, Integer order) {
        this.category = category;
        this.freeShiping = freeShiping;
        this.productName = productName;
        this.brandName = brandName;
        this.order = order;
    }

    @Override
    public Filter convert() {
        return new Filter(category, freeShiping, productName, brandName, order);
    }

    @Override
    public FilterDTO convert(Filter filter) {
        this.category = filter.getCategory();
        this.freeShiping = filter.getFreeShiping();
        this.productName = filter.getProductName();
        this.brandName = filter.getBrandName();
        this.order = filter.getOrder();
        return this;
    }
}
