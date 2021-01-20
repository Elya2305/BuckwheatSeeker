package com.progastination.utils.client;

import com.progastination.dto.ProductResponseDto;
import com.progastination.entity.Shop;

public interface ProductClient {

    ProductResponseDto products(Shop shop);
}
