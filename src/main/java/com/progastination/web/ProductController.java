package com.progastination.web;

import com.progastination.entity.Shop;
import com.progastination.repository.ProductRepository;
import com.progastination.utils.client.ProductClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductClient productClient;
    private final ProductRepository productRepository;

    @GetMapping
    private List<Set<Shop>> all() {
        return productRepository.getDistinctShops();
    }
}
