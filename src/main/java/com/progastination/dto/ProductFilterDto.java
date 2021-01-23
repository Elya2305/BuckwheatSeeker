package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.progastination.entity.Shop;
import com.progastination.utils.select.SortPrice;
import lombok.Data;

import java.util.List;

/**
 * @author Elya
 * you can add any filed you want, or remove too
 * @see com.progastination.service.impl.ProductFilterServiceImpl
 */
@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductFilterDto {
    private List<Integer> weights;
    private List<String> categories;
    private String search;
    private SortPrice sortPrice;
    private List<Shop> shops;
}
