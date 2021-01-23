package com.progastination.dto;

import com.progastination.entity.Shop;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopPriceDto {
    private Shop shop;
    private Double price;
}
