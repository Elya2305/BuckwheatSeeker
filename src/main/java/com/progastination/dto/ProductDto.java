package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductDto {
    private String ean;
    private String categoryId;
    private ImgDto img;
    private Integer price;
    private String title;
    private String webUrl;
    private Integer weight;
}
