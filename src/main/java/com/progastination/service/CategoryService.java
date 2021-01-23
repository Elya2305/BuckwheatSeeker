package com.progastination.service;

import com.progastination.dto.CategoryIdentifiersDto;
import com.progastination.dto.CategoryImprovedDto;
import com.progastination.utils.pagination.PageDto;

public interface CategoryService {
    PageDto<CategoryImprovedDto> mainCategories(int page, int pageSize);

    PageDto<CategoryImprovedDto> subCategories(CategoryIdentifiersDto categories, int page, int pageSize);
}
