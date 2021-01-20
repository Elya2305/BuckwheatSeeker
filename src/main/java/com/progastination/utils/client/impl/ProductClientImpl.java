package com.progastination.utils.client.impl;

import com.progastination.dto.ListProductDtoResponse;
import com.progastination.dto.ProductDto;
import com.progastination.utils.AbstractHttpClient;
import com.progastination.utils.client.ProductClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductClientImpl extends AbstractHttpClient implements ProductClient {
    private static final String AUCHAN_PRODUCTS = "https://stores-api.zakaz.ua/stores/48246407/categories/fruits-and-vegetables-auchan/products";

    public ProductClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public List<ProductDto> productsAuchan() {
        return get(AUCHAN_PRODUCTS, ListProductDtoResponse.class);
    }

    @Override
    public HttpHeaders headers() {
        return new HttpHeaders();
    }
}
