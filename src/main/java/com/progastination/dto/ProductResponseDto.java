package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.progastination.entity.Shop;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductResponseDto {
    private String ean;
    private String categoryId;
    private ImgDto img;
    private Double price;
    private String title;
    private String webUrl;
    private Integer weight;
    private String unit;
    private ProducerDto producer;
    private Shop shop;
}
