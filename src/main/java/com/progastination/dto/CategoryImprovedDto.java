package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.progastination.entity.Shop;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CategoryImprovedDto {
    private String title;
    private String image;
    private Integer totalProducts;
    private Integer totalChildren;
    private List<String> identifiers;
    private List<String> parents;
    private List<Shop> shops;
}
