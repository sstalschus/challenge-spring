package com.bootcamp.challenge.spring.dtos;

import com.bootcamp.challenge.spring.dtos.interfaces.ConvertDTO;
import com.bootcamp.challenge.spring.entities.Filter;
import lombok.Getter;
import lombok.Setter;

/** DTO de filtros
 *
 * @author Arthur Amorim
 *
 * */
@Getter
@Setter
public class FilterDTO implements ConvertDTO<Filter, FilterDTO> {

    private String category;
    private Boolean freeShiping;
    private String productName;
    private String brandName;
    private Integer order;

    public FilterDTO(String category, Boolean freeShiping, String productName, String brandName, Integer order) {
        this.category = category;
        this.freeShiping = freeShiping;
        this.productName = productName;
        this.brandName = brandName;
        this.order = order;
    }

    /** Método usado para converter a entidade em um Filtro.
     *
     * @author Arthur Amorim
     *
     * @return Filtro convertida
     *
     * */
    @Override
    public Filter convert() {
        return new Filter(category, freeShiping, productName, brandName, order);
    }

    /** Método usado para converter a entidade filtro em filtroDTO .
     *
     * @author Arthur Amorim
     *
     * @param  filter - Entidade Pedido a ser convertida em filterDTO.
     *
     * @return Filter convertido
     *
     * */
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
