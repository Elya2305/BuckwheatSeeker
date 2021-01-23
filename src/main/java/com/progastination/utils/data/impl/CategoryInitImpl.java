package com.progastination.utils.data.impl;

import com.progastination.dto.CategoryDto;
import com.progastination.entity.Category;
import com.progastination.entity.Shop;
import com.progastination.repository.CategoryRepository;
import com.progastination.utils.client.CategoryClient;
import com.progastination.utils.data.InitDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

// todo change to update
@Slf4j
@Service(value = CategoryInitImpl.CATEGORY_INITIALIZER)
@AllArgsConstructor
public class CategoryInitImpl implements InitDbService {
    public static final String CATEGORY_INITIALIZER = "CategoryInit";
    private final CategoryClient categoryClient;
    private final CategoryRepository categoryRepository;
    private final static String DELIMITER = "-";

    @Override
    public void init() {
        if (categoryRepository.count() == 0) {
            log.info("*starting to init categories*");
            List<String> categories = Category.ShopCategory.categoryIdentifiers();

            Arrays.stream(Shop.values()).forEach(shop -> categoryClient.categories(shop)
                    .stream().filter(o -> categories.contains(getIdentifier(o.getParentId())) || categories.contains(getIdentifier(o.getId()))).forEach(this::mapAndSave));
            log.info("*ending to init categories*");
        }
    }

    @Override
    public void clear() {
        categoryRepository.deleteAll();
    }

    @Override
    public boolean isEmpty() {
        return categoryRepository.count() == 0;
    }

    private void mapAndSave(CategoryDto dto) {
        if (nonNull(dto) && identifierExists(dto.getId())) {
            categoryRepository.save(map(dto));
            dto.getChildren().forEach(this::mapAndSave);
        }
    }

    private Category map(CategoryDto source) {
        Category destination = getOrCreate(getIdentifier(source.getId()));
        destination.setCount(source.getCount());
        destination.setIdentifier(getIdentifier(source.getId()));
        destination.setImage(source.getImageUrl());
        destination.setTitle(source.getTitle());
        destination.addShop(getShopByIdentifier(source.getId()));
        destination.setCategory(get(getIdentifier(source.getParentId())));
        return destination;
    }

    private Category getOrCreate(String identifier) {
        return categoryRepository.findByIdentifier(identifier).orElse(new Category());
    }

    private Category get(String identifier) {
        return categoryRepository.findByIdentifier(identifier).orElse(null);
    }

    private String getIdentifier(String fullIdentifier) {
        if (isNull(fullIdentifier)) return null;
        return Arrays.stream(fullIdentifier.split(DELIMITER))
                .filter(o -> !Shop.allIdentifiers().contains(o))
                .collect(Collectors.joining(DELIMITER));
    }

    private Shop getShopByIdentifier(String fullIdentifier) {
        String[] all = fullIdentifier.split(DELIMITER);
        return Shop.getByIdentifier(all[all.length - 1]);
    }

    private boolean identifierExists(String fullIdentifier) {
        String[] all = fullIdentifier.split(DELIMITER);
        return Shop.allIdentifiers().contains(all[all.length - 1]);
    }
}
