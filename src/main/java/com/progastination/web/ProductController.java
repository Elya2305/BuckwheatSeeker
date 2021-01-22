package com.progastination.web;

import com.progastination.dto.ProducerDto;
import com.progastination.dto.ProductChartDto;
import com.progastination.dto.ProductDto;
import com.progastination.entity.Product;
import com.progastination.entity.Shop;
import com.progastination.repository.ProductRepository;
import com.progastination.service.ProductChartService;
import com.progastination.service.ProductService;
import com.progastination.utils.pagination.PageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductChartService productChartService;

    @GetMapping("/by-category")
    public PageDto<ProductDto> byCategory(@RequestParam String category,
                                              @RequestParam int page,
                                              @RequestParam int pageSize) {
        log.info("Request on getting products by category");
        return productService.productsByCategory(category, page, pageSize);
    }

    @GetMapping("/by-category/shop")
    public PageDto<ProductDto> byCategoryAndShop(@RequestParam String category,
                                                 @RequestParam Shop shop,
                                                 @RequestParam int page,
                                                 @RequestParam int pageSize) {
        log.info("Request on getting products by category and shop");
        return productService.productsByCategoryAndShop(category, shop.getName(), page, pageSize);
    }
    @GetMapping("/by-category/chart")
    public ProductChartDto getChart() throws IOException {
        log.info("Request on getting product chart");
        return productChartService.getProductChart();
    }
}
