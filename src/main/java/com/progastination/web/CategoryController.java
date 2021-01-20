package com.progastination.web;

import com.progastination.dto.CategoryDto;
import com.progastination.service.CategoryService;
import com.progastination.utils.pagination.PageDto;
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
    public PageDto<CategoryDto> mainCategories(@RequestParam int page,
                                               @RequestParam int pageSize) {
        log.info("Request on getting main categories");
        return categoryService.mainCategories(page, pageSize);
    }

    @GetMapping("/sub-categories")
    public PageDto<CategoryDto> subCategories(@RequestParam String identifier,
                                           @RequestParam int page,
                                           @RequestParam int pageSize) {
        log.info("Request on getting sub categories by identifier {}", identifier);
        return categoryService.subCategories(identifier, page, pageSize);
    }
}
