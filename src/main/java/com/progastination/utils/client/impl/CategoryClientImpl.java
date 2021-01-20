package com.progastination.utils.client.impl;

import com.progastination.dto.CategoryDto;
import com.progastination.dto.ListDtoResponse;
import com.progastination.utils.AbstractHttpClient;
import com.progastination.utils.client.CategoryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryClientImpl extends AbstractHttpClient implements CategoryClient {
    private static final String AUCHAN_CATEGORIES = "https://stores-api.zakaz.ua/stores/48246407/categories/";

    public CategoryClientImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public List<CategoryDto> categoriesAuchan() {
        return get(AUCHAN_CATEGORIES, ListDtoResponse.class);
    }

    @Override
    public HttpHeaders headers() {
        return new HttpHeaders();
    }
}
