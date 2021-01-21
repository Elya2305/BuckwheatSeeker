package com.progastination.service;

import com.progastination.dto.ProductDto;
import com.progastination.utils.pagination.PageDto;

public interface ProductService {
    PageDto<ProductDto> productsByCategory(String category, int page, int pageSize);

    PageDto<ProductDto> productsByCategoryAndShop(String category, String shopIdentifier, int page, int pageSize);
}
