package com.progastination.service;

import com.progastination.dto.CategoryImprovedDto;
import com.progastination.utils.pagination.PageDto;

public interface CategoryService {
    PageDto<CategoryImprovedDto> mainCategories(int page, int pageSize);

    PageDto<CategoryImprovedDto> subCategories(String identifier, int page, int pageSize);
}
