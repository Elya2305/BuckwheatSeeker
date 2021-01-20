package com.progastination.utils.data;

import com.progastination.dto.CategoryDto;
import com.progastination.dto.ProductDto;
import com.progastination.entity.Category;
import com.progastination.entity.Product;
import com.progastination.entity.Shop;
import com.progastination.repository.CategoryRepository;
import com.progastination.repository.ProductRepository;
import com.progastination.utils.client.CategoryClient;
import com.progastination.utils.client.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Component
@AllArgsConstructor
public class ProductSaveToDb {
    private final ProductClient productClient;
    private final ProductRepository productRepository;
    private final static String DELIMITER = "-";
    private final static String LETTER_PATTERN = "[^\\d.]";

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) {
            loadDb();
        }
    }

    public void loadDb() {
        log.info("*starting to init products*");
        Arrays.stream(Shop.values()).forEach(shop -> productClient.products(shop).forEach(this::mapAndSave));
        log.info("*ending to init products*");
    }

    private void mapAndSave(ProductDto dto) {
        if (nonNull(dto) && identifierExists(dto.getId()))
            productRepository.save(map(dto));
    }

    private Product map(ProductDto source) {
        Product destination = getOrCreate(getIdentifier(source.getId()));
        destination.setIdentifier(getIdentifier(source.getId()));
        destination.setCategoryId(source.getCategoryId());
        destination.setEan(getEan(source.getEan()));
        destination.setImage(source.getImg());
        destination.setPrice(source.getPrice());
        destination.setTitle(source.getTitle());
        destination.setWebUrl(source.getWebUrl());
        destination.setWeight(source.getWeight());
        destination.addShop(getShopByIdentifier(source.getId()));
        return destination;
    }

    private Product getOrCreate(String identifier) {
        return productRepository.findByIdentifier(identifier).orElse(new Product());
    }

    private String getIdentifier(String fullIdentifier) {
        if (isNull(fullIdentifier)) return null;
        return Arrays.stream(fullIdentifier.split(DELIMITER))
                .filter(o -> !Shop.allIdentifiers().contains(o))
                .collect(Collectors.joining(DELIMITER));
    }

    private String getEan(String fullEan) {
        if (isNull(fullEan)) return null;
        return fullEan.replaceAll(LETTER_PATTERN,"");
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
