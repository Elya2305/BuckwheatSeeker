package com.progastination.utils.client;

import com.progastination.dto.CategoryDto;
import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductsListDto;
import com.progastination.entity.Shop;

import java.util.List;

public interface ProductClient {

    List<ProductsListDto> products(Shop shop);
}
