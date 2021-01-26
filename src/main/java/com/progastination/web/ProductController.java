package com.progastination.web;

import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;
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
