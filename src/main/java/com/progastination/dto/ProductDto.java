package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Map;
// todo productBaseDto

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductDto {
    private String ean;
    private String categoryId;
    private ImgDto img;
    private String img_url;
    private Double price;
    private String title;
    private String webUrl;
    private Integer weight;
    private String unit;
    private String shop;
    private ProducerDto producer;
}
