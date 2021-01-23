package com.progastination.web;

import com.progastination.dto.CategoryIdentifiersDto;
import com.progastination.dto.CategoryImprovedDto;
import com.progastination.service.CategoryService;
import com.progastination.utils.pagination.PageDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/main")
    public PageDto<CategoryImprovedDto> mainCategories(@RequestParam int page,
                                                       @RequestParam int pageSize) {
        log.info("Request on getting main categories");
        return categoryService.mainCategories(page, pageSize);
    }

    @PostMapping("/sub-categories")
    public PageDto<CategoryImprovedDto> subCategories(@RequestBody CategoryIdentifiersDto categories,
                                                      @RequestParam int page,
                                                      @RequestParam int pageSize) {
        log.info("Request on getting sub categories by identifiers {}", categories);
        return categoryService.subCategories(categories, page, pageSize);
    }
}
