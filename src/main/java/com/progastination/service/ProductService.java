package com.progastination.service;

import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;
import com.progastination.utils.pagination.PageDto;

public interface ProductService {
    PageDto<ProductDto> productsByCategory(String category, int page, int pageSize);

    PageDto<ProductDto> productsByCategoryAndShop(String category, String shop, int page, int pageSize);

    PageDto<ProductDto> productsBySearch(String search, int page, int pageSize);

    PageDto<ProductDto> productsByFilter(ProductFilterDto filter, int page, int pageSize);
}
