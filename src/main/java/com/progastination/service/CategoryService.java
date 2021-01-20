package com.progastination.service;

import com.progastination.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> mainCategories();

    List<CategoryDto> subCategories(String identifier);
}
