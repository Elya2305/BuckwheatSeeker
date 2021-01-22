package com.progastination.service.impl;

import com.progastination.dto.ProductChartDto;
import com.progastination.service.ProductChartService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProductChartServiceImpl implements ProductChartService {
    private static final String CHART_URL = "https://index.minfin.com.ua/markets/wares/prods/?c=%D0%B1%D0%B0%D0%BA%D0%B0%D0%BB%D0%B5%D1%8F&g=%D0%BA%D1%80%D1%83%D0%BF%D1%8B&k=%D0%B3%D1%80%D0%B5%D1%87%D0%BA%D0%B0%3D%3D%3D+1%D0%BA%D0%B3";
    private static final String ELEMENT_KEY_ALIGN = "align";
    private static final String ELEMENT_VALUE_RIGHT = "right";
    private static final String ELEMENT_VALUE_CENTER = "center";
    private static final String COMA = ",";
    private static final String DOT = ".";


    @Override
    public ProductChartDto getProductChart() throws IOException {
        ProductChartDto productChartDto = new ProductChartDto();
        Document document = Jsoup.connect(CHART_URL).get();
        Elements values = document.getElementsByAttributeValueContaining(ELEMENT_KEY_ALIGN, ELEMENT_VALUE_RIGHT);
        Elements dates = document.getElementsByAttributeValueContaining(ELEMENT_KEY_ALIGN, ELEMENT_VALUE_CENTER);
        values.stream().skip(12).forEach(e -> {
            productChartDto.getValues().add(Double.valueOf(e.text().replace(COMA, DOT)));
        });
        dates.stream()
                .skip(6)
                .filter(element -> element.text().length() > 2)
                .forEach(e -> {
                    productChartDto.getDates().add(e.text());
                });
        return productChartDto;
    }
}
