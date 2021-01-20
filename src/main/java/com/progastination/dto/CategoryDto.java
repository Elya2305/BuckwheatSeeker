package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CategoryDto {
    private List<CategoryDto> children;
    private Integer count;
    private String id;
    private String imageUrl;
    private String parentId;
    private String title;
}
