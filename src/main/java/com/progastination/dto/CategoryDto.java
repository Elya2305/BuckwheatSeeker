package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.progastination.entity.Shop;
import lombok.Data;

import java.util.Set;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CategoryDto {
    private Integer count;
    private String id;
    private String imageUrl;
    private String parentId;
    private String title;
    private Set<CategoryDto> children;
    private Set<Shop> shopAvailable;
}
