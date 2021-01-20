package com.progastination.web;

import com.progastination.dto.CategoryDto;
import com.progastination.entity.Shop;
import com.progastination.utils.client.CategoryClient;
import com.progastination.utils.data.CategorySaveToDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryClient categoryClient;
    private final CategorySaveToDb categorySaveToDb;

    @GetMapping
    private List<CategoryDto> all() {
        return categoryClient.categories(Shop.EKO_MARKET);
    }
}
