package com.progastination.service;

import com.progastination.dto.ProductDto;
import com.progastination.entity.Shop;
import com.progastination.utils.pagination.PageDto;

import java.util.Set;

public interface ProductService {
    PageDto<ProductDto> productsByCategory(String category, int page, int pageSize);

    PageDto<ProductDto> productsByCategoryAndShop(String category, String shop, int page, int pageSize);
}
