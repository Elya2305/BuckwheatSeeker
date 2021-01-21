package com.progastination.utils.client.impl;

import com.progastination.dto.ProductResponseDto;
import com.progastination.entity.Shop;
import com.progastination.utils.AbstractHttpClient;
import com.progastination.utils.client.ProductClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductClientImpl extends AbstractHttpClient implements ProductClient {
    private final Map<Shop, String> productUrls;
    private static final String AUCHAN_PRODUCTS = "https://stores-api.zakaz.ua/stores/48246401/categories/[category]/products";
    private static final String METRO_CATEGORIES = "https://stores-api.zakaz.ua/stores/48215611/categories/[category]/products";
    private static final String EKO_MARKET_CATEGORIES = "https://stores-api.zakaz.ua/stores/48280214/categories/[category]/products";

    {
        productUrls = new HashMap<>();
        productUrls.put(Shop.AUCHAN, AUCHAN_PRODUCTS);
        productUrls.put(Shop.METRO, METRO_CATEGORIES);
        productUrls.put(Shop.EKO_MARKET, EKO_MARKET_CATEGORIES);
    }

    public ProductClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public ProductResponseDto products(String category, Shop shop) {
        return get(url(category, shop), ProductResponseDto.class);
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
}
