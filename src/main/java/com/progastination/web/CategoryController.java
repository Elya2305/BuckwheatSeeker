package com.progastination.web;

import com.progastination.dto.CategoryDto;
import com.progastination.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/main")
    public List<CategoryDto> mainCategories() {
        log.info("Request on getting main categories");
        return categoryService.mainCategories();
    }

    @GetMapping("/sub-categories")
    public List<CategoryDto> subCategories(@RequestParam String identifier) {
        log.info("Request on getting sub categories by identifier {}", identifier);
        return categoryService.subCategories(identifier);
    }
}
