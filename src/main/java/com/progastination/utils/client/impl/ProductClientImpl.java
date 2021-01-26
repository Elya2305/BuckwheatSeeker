package com.progastination.utils.client.impl;

import com.progastination.dto.ProductResponseListDto;
import com.progastination.entity.Shop;
import com.progastination.utils.AbstractHttpClient;
import com.progastination.utils.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ProductClientImpl extends AbstractHttpClient implements ProductClient {
    private final Map<Shop, String> productUrls;
    private static final String AUCHAN_CATEGORIES = "https://stores-api.zakaz.ua/stores/48246401/categories/[category]/products";
    private static final String METRO_CATEGORIES = "https://stores-api.zakaz.ua/stores/48215611/categories/[category]/products";
    private static final String EKO_MARKET_CATEGORIES = "https://stores-api.zakaz.ua/stores/48280214/categories/[category]/products";

    {
        productUrls = new HashMap<>();
        productUrls.put(Shop.AUCHAN, AUCHAN_CATEGORIES);
        productUrls.put(Shop.METRO, METRO_CATEGORIES);
        productUrls.put(Shop.EKO_MARKET, EKO_MARKET_CATEGORIES);
    }

    public ProductClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public ProductResponseListDto products(String category, Shop shop) {
        try {
            return get(url(category, shop), ProductResponseListDto.class);
        } catch (RuntimeException e) {
            log.error("error while getting product by url {}", url(category, shop));
            return emptyResponse();
        }
    }

    @Override
    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept-language", "ru-RU");
        return headers;
    }

    private String url(String category, Shop shop) {
        return productUrls.get(shop).replace("[category]", category);
    }

    private ProductResponseListDto emptyResponse() {
        ProductResponseListDto response = new ProductResponseListDto();
        response.setResults(Collections.emptyList());
        return response;
    }
}
