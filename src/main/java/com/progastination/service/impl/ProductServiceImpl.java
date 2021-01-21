package com.progastination.service.impl;

import com.progastination.dto.CategoryDto;
import com.progastination.dto.ImgDto;
import com.progastination.dto.ProductDto;
import com.progastination.entity.Category;
import com.progastination.entity.Product;
import com.progastination.repository.ProductRepository;
import com.progastination.service.ProductService;
import com.progastination.utils.pagination.PageDto;
import com.progastination.utils.pagination.PagesUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public PageDto<ProductDto> productsByCategory(String category, int page, int pageSize) {
        Page<Product> result = productRepository.findAllByCategory(category, PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, map(result.getContent()));
    }

    @Override
    public PageDto<ProductDto> productsByCategoryAndShop(String category, String shopIdentifier, int page, int pageSize) {
        return null;
    }

    private List<ProductDto> map(List<Product> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    private ProductDto map(Product source) {
        ProductDto destination = new ProductDto();
        destination.setCategoryId(source.getCategoryId());
        destination.setEan(source.getEan());
        destination.setImg(ImgDto.of(source.getImage()));
        destination.setPrice(source.getPrice());
        destination.setWebUrl(source.getWebUrl());
        destination.setTitle(source.getTitle());
        destination.setWeight(source.getWeight());
        return destination;
    }
}
