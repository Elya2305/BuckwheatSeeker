package com.progastination.service;

import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;

import java.util.List;

public interface ProductFilterService {
    List<ProductDto> filterProducts(ProductFilterDto filter, int page, int pageSize);
}
