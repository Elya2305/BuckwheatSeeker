package com.progastination.web;

import com.progastination.dto.ProductDto;
import com.progastination.entity.Shop;
import com.progastination.service.ProductService;
import com.progastination.utils.pagination.PageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/by-category")
    public PageDto<ProductDto> byCategory(@RequestParam String category,
                                              @RequestParam int page,
                                              @RequestParam int pageSize) {
        log.info("Request on getting products by categories");
        return productService.productsByCategory(category, page, pageSize);
    }

    @GetMapping("/by-category/shop")
    public PageDto<ProductDto> byShop(@RequestParam String category,
                                      @RequestParam String shopIdentifier,
                                              @RequestParam int page,
                                              @RequestParam int pageSize) {
        log.info("Request on getting main categories");
        return productService.productsByCategoryAndShop(category, shopIdentifier, page, pageSize);
    }
}
