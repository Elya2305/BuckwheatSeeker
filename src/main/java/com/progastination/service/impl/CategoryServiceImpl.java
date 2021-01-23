package com.progastination.service.impl;

import com.progastination.dto.CategoryIdentifiersDto;
import com.progastination.dto.CategoryImprovedDto;
import com.progastination.entity.projection.CategoryProjection;
import com.progastination.repository.CategoryRepository;
import com.progastination.service.CategoryService;
import com.progastination.utils.converter.ShopConverter;
import com.progastination.utils.pagination.PageDto;
import com.progastination.utils.pagination.PagesUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final static String DELIMITER = ",";
    private final CategoryRepository categoryRepository;
    private final ShopConverter shopConverter = new ShopConverter();

    @Override
    public PageDto<CategoryImprovedDto> mainCategories(int page, int pageSize) {
        Page<CategoryProjection> result = categoryRepository.mainCategories(PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, mapCategoryImproved(result.getContent()));
    }

    @Override
    public PageDto<CategoryImprovedDto> subCategories(CategoryIdentifiersDto categories, int page, int pageSize) {
        Page<CategoryProjection> result = categoryRepository.findSubCategories(categories.getCategories(), PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, mapCategoryImproved(result.getContent()));
    }

    private List<CategoryImprovedDto> mapCategoryImproved(List<CategoryProjection> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    private CategoryImprovedDto map(CategoryProjection source) {
        List<String> identifiers = Arrays.asList(source.getIdentifiers().split(DELIMITER));
        CategoryImprovedDto destination = new CategoryImprovedDto();
        destination.setIdentifiers(identifiers);
        destination.setImage(source.getImage());
        destination.setParents(mapParent(source.getParent()));
        destination.setShops(shopConverter.convertFromStringArray(source.getShops()));
        destination.setTitle(source.getTitle());
        destination.setTotalProducts(source.getTotalCount());
        destination.setTotalChildren(categoryRepository.countChildren(identifiers));
        return destination;
    }

    private List<String> mapParent(String source) {
        if (isNull(source)) return null;
        return Arrays.asList(source.split(DELIMITER));
    }
}
