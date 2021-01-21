package com.progastination.utils.data.impl;

import com.progastination.dto.ProductDto;
import com.progastination.entity.Category;
import com.progastination.entity.Product;
import com.progastination.entity.Shop;
import com.progastination.repository.CategoryRepository;
import com.progastination.repository.ProductRepository;
import com.progastination.service.ProductService;
import com.progastination.utils.client.ProductClient;
import com.progastination.utils.data.InitDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service(value = ProductInitImpl.PRODUCT_INITIALIZER)
@AllArgsConstructor
public class ProductInitImpl implements InitDbService {
    public final static String PRODUCT_INITIALIZER = "ProductInit";
    private final static String DELIMITER = "-";
    private final static String LETTER_PATTERN = "[^\\d.]";
    private final ProductClient productClient;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void init() {
        log.info("*starting to init products*");
        List<Category> categories = categoryRepository.findAll();
        for (Shop shop : Shop.values()) {
            for (Category category : categories) {
                if (category.getShops().contains(shop)) {
                    List<ProductDto> results = productClient.products(getFullIdentifier(category.getIdentifier(), shop), shop).getResults();
                    List<Product> map = map(results, category, shop);
                    productRepository.saveAll(map);
                }
            }
        }
        log.info("*ending to init products*");
    }

    @Override
    public void clear() {
        productRepository.deleteAll();
    }

    @Override
    public boolean isEmpty() {
        return productRepository.count() == 0;
    }

    private List<Product> map(List<ProductDto> source, Category category, Shop shop) {
        return source.stream().map(this::map).peek(o -> {
            o.setCategory(category);
            o.getShops().add(shop);
        }).collect(Collectors.toList());
    }

    private Product map(ProductDto source) {
        Product destination = getOrCreate(getEan(source.getEan()));
        destination.setCategoryId(getCategoryId(source.getCategoryId()));
        destination.setEan(getEan(source.getEan()));
        destination.setImage(source.getImg().getS350x350());
        destination.setPrice(source.getPrice());
        destination.setTitle(source.getTitle());
        destination.setWebUrl(source.getWebUrl());
        destination.setWeight(source.getWeight());
        destination.addShop(getShopByCategoryId(source.getCategoryId()));
        return destination;
    }

    private Product getOrCreate(String ean) {
        return productRepository.findByEan(ean).orElse(new Product());
    }

    private String getCategoryId(String fullIdentifier) {
        if (isNull(fullIdentifier)) return null;
        return Arrays.stream(fullIdentifier.split(DELIMITER))
                .filter(o -> !Shop.allIdentifiers().contains(o))
                .collect(Collectors.joining(DELIMITER));
    }

    private String getEan(String fullEan) {
        if (isNull(fullEan)) return null;
        return fullEan.replaceAll(LETTER_PATTERN, "");
    }

    private Shop getShopByCategoryId(String fullIdentifier) {
        String[] all = fullIdentifier.split(DELIMITER);
        return Shop.getByIdentifier(all[all.length - 1]);
    }

    // todo remove kostil and add extra column to Category
    private String getFullIdentifier(String shortIdentifier, Shop shop) {
        String result = shortIdentifier + DELIMITER + shop.getIdentifier();
        if ("pizza-and-breads".equals(shortIdentifier) && Shop.AUCHAN.equals(shop)) {
            return result + DELIMITER + shop.getIdentifier();
        }
        return result;
    }
}
