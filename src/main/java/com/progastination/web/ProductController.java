package com.progastination.web;

import com.progastination.dto.ProductDto;
import com.progastination.utils.client.ProductClient;
import com.progastination.utils.data.ProductSaveToDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/зкщвгсеы")
public class ProductController {
    private final ProductClient productClient;
    private final ProductSaveToDb productSaveToDb;

//    @GetMapping
//    private List<ProductDto> all() {
//        return productClient.products();
//    }
}
