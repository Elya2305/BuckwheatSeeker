package com.progastination.service.impl;

import com.progastination.dto.CategoryDto;
import com.progastination.entity.Category;
import com.progastination.repository.CategoryRepository;
import com.progastination.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> mainCategories() {
        return categoryRepository.findAllByCategoryIsNull()
                .stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> subCategories(String identifier) {
        return categoryRepository.findSubCategories(identifier)
                .stream().map(this::map).collect(Collectors.toList());
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
