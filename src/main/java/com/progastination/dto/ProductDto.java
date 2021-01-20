package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductDto {
    private String category_id;
    private String ean;
    private List<String> gallery;
    private List<String> img;
    private Integer price;
    private String title;
    private String web_url;
    private Integer weight;

}
