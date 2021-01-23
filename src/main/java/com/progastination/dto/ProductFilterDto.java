package com.progastination.dto;

import lombok.Data;

import java.util.List;
/**
* @author Elya
* tou can add any filed you want, or remove too
* @see com.progastination.service.impl.ProductFilterServiceImpl
* */
@Data
public class ProductFilterDto {
    private List<Integer> weights;
    private List<String> categoryIdentifiers;
    private String search;
}
