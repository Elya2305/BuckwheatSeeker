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
    private final Map<Shop, String> shopUrls;


    private static final String AUCHAN_PRODUCTS = "https://stores-api.zakaz.ua/stores/48246407/categories/fruits-and-vegetables-auchan/products";

    {
        shopUrls = new HashMap<>();
        shopUrls.put(Shop.AUCHAN, AUCHAN_PRODUCTS);
    }

    public ProductClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public ProductResponseDto products(Shop shop) {
        System.out.println(shopUrls.get(shop));
        return get(AUCHAN_PRODUCTS, ProductResponseDto.class);
    }

    @Override
    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept-language", "ru-RU");
        return headers;
    }
}
