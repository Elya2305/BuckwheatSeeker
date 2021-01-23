package com.progastination.dto;

import com.progastination.entity.Shop;
import lombok.Data;

import java.util.List;

@Data
public class CategoryImprovedDto {
    private String title;
    private String image;
    private Integer totalProducts;
    private Integer totalChildren;
    private List<String> identifiers;
    private List<String> parents;
    private List<Shop> shops;
}
