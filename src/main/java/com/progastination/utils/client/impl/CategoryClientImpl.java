package com.progastination.utils.client.impl;

import com.progastination.dto.CategoryDto;
import com.progastination.dto.ListCategoryDtoResponse;
import com.progastination.entity.Shop;
import com.progastination.utils.AbstractHttpClient;
import com.progastination.utils.client.CategoryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryClientImpl extends AbstractHttpClient implements CategoryClient {
    private final Map<Shop, String> shopUrls;

    private static final String AUCHAN_CATEGORIES = "https://stores-api.zakaz.ua/stores/48246401/categories";
    private static final String METRO_CATEGORIES = "https://stores-api.zakaz.ua/stores/48215611/categories";
    private static final String EKO_MARKET_CATEGORIES = "https://stores-api.zakaz.ua/stores/48280214/categories/";

    {
        shopUrls = new HashMap<>();
        shopUrls.put(Shop.AUCHAN, AUCHAN_CATEGORIES);
        shopUrls.put(Shop.METRO, METRO_CATEGORIES);
        shopUrls.put(Shop.EKO_MARKET, EKO_MARKET_CATEGORIES);
    }

    public CategoryClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public List<CategoryDto> categories(Shop shop) {
        return get(shopUrls.get(shop), ListCategoryDtoResponse.class);
    }

    @Override
    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept-language", "ru-RU");
        return headers;
    }
}
