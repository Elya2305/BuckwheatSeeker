package com.progastination.utils.client;

import com.progastination.dto.CategoryDto;
import com.progastination.entity.Shop;

import java.util.List;

public interface CategoryClient {

    List<CategoryDto> categories(Shop shop);
}
