package com.progastination.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {

    private List<ProductDto> results;
}
