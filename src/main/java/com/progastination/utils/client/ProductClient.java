package com.progastination.utils.client;

import com.progastination.dto.ProductResponseListDto;
import com.progastination.entity.Shop;

public interface ProductClient {

    ProductResponseListDto products(String category, Shop shop);

}
