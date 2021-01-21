package com.progastination.service.impl;

import com.progastination.dto.CategoryDto;
import com.progastination.entity.Category;
import com.progastination.repository.CategoryRepository;
import com.progastination.service.CategoryService;
import com.progastination.utils.pagination.PageDto;
import com.progastination.utils.pagination.PagesUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public PageDto<CategoryDto> mainCategories(int page, int pageSize) {
        Page<Category> result = categoryRepository.findAllByCategoryIsNull(PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, map(result.getContent()));
    }

    @Override
    public PageDto<CategoryDto> subCategories(String identifier, int page, int pageSize) {
        Page<Category> result = categoryRepository.findSubCategories(identifier, PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, map(result.getContent()));
    }

    private List<CategoryDto> map(List<Category> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    private CategoryDto map(Category source) {
        CategoryDto destination = new CategoryDto();
        destination.setCount(source.getCount());
        destination.setId(source.getIdentifier());
        destination.setTitle(source.getTitle());
        destination.setImageUrl(source.getImage());
        destination.setShopAvailable(source.getShops());
        return destination;
    }
}
