package com.progastination.web;

import com.progastination.dto.ProductResponseDto;
import com.progastination.entity.Shop;
import com.progastination.utils.client.ProductClient;
import com.progastination.utils.data.ProductSaveToDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductClient productClient;
    private final ProductSaveToDb productSaveToDb;

    @GetMapping
    private ProductResponseDto all() {
        return productClient.products(Shop.AUCHAN);
    }
}
