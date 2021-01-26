package com.progastination.service.impl;

import com.progastination.dto.ImgDto;
import com.progastination.dto.ProductDto;
import com.progastination.dto.ProductFilterDto;
import com.progastination.entity.Product;
import com.progastination.entity.Shop;
import com.progastination.repository.ProductRepository;
import com.progastination.service.ProductFilterService;
import com.progastination.service.ProductService;
import com.progastination.utils.pagination.PageDto;
import com.progastination.utils.pagination.PagesUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductFilterService productFilterService;

    @Override
    public PageDto<ProductDto> productsBySearch(String search, int page, int pageSize) {
        Page<Product> result = productRepository.findByNameOrProducer(search, PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, map(result.getContent()));
    }

    @Override
    public PageDto<ProductDto> productsByFilter(ProductFilterDto filter, int page, int pageSize) {
        List<ProductDto> products = productFilterService.filterProducts(filter, page, pageSize);
        return PageDto.of(products.size(), page, products);
    }

    @Override
    public PageDto<ProductDto> buckwheatSearch(String category, int page, int pageSize) {
        Page<Product> result = productRepository.findAllByCategoryAndShop(category,PagesUtility.createPageableUnsorted(page, pageSize));
        return PageDto.of(result.getTotalElements(), page, map(result.getContent()));
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
        destination.setUnit(source.getUnit());
        destination.setShop(source.getShop());
        return destination;
    }

    private Map<String, Integer> map(Map<Shop, Integer> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                o -> o.getKey().name(),
                Map.Entry::getValue
        ));
    }
}
