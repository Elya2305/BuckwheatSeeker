package com.progastination.service;

import com.progastination.dto.CategoryDto;
import com.progastination.utils.pagination.PageDto;

import java.util.List;

public interface CategoryService {
    PageDto<CategoryDto> mainCategories(int page, int pageSize);

    PageDto<CategoryDto> subCategories(String identifier, int page, int pageSize);
}
