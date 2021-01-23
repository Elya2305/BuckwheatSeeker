package com.progastination.web;

import com.progastination.dto.ProductChartDto;
import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;
import com.progastination.entity.Shop;
import com.progastination.service.ProductChartService;
import com.progastination.service.ProductService;
import com.progastination.utils.pagination.PageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductChartService productChartService;

    @GetMapping("/by-category")
    public PageDto<ProductDto> byCategory(@RequestParam String category,
                                          @RequestParam int page,
                                          @RequestParam int pageSize) {
        log.info("Request on getting products by category - {}", category);
        return productService.productsByCategory(category, page, pageSize);
    }

    @GetMapping("/by-category/shop")
    public PageDto<ProductDto> byCategoryAndShop(@RequestParam String category,
                                                 @RequestParam Shop shop,
                                                 @RequestParam int page,
                                                 @RequestParam int pageSize) {
        log.info("Request on getting products by category - {},  shop - {}", shop, category);
        return productService.productsByCategoryAndShop(category, shop.getName(), page, pageSize);
    }

    @GetMapping("/by-category/chart")
    public ProductChartDto getChart() {
        log.info("Request on getting product chart");
        return productChartService.getProductChart();
    }

    @GetMapping("/search")
    public PageDto<ProductDto> productGlobalSearch(@RequestParam String search,
                                                   @RequestParam int page,
                                                   @RequestParam int pageSize) {
        log.info("Request on getting products by search - {}", search);
        return productService.productsBySearch(search, page, pageSize);
    }

    @PostMapping("/filter")
    public PageDto<ProductDto> productFilter(@RequestBody ProductFilterDto filter,
                                             @RequestParam int page,
                                             @RequestParam int pageSize) {
        log.info("Request on getting products by filter - {}", filter);
        return productService.productsByFilter(filter, page, pageSize);
    }
}
