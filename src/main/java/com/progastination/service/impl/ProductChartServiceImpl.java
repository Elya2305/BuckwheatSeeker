package com.progastination.service.impl;

import com.progastination.dto.ProductChartDto;
import com.progastination.service.ProductChartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// todo refactor
@Slf4j
@Service
@AllArgsConstructor
public class ProductChartServiceImpl implements ProductChartService {
    private static final String CHART_URL = "https://index.minfin.com.ua/markets/wares/prods/?c=%D0%B1%D0%B0%D0%BA%D0%B0%D0%BB%D0%B5%D1%8F&g=%D0%BA%D1%80%D1%83%D0%BF%D1%8B&k=%D0%B3%D1%80%D0%B5%D1%87%D0%BA%D0%B0%3D%3D%3D+1%D0%BA%D0%B3";
    private static final String ELEMENT_KEY_ALIGN = "align";
    private static final String ELEMENT_VALUE_RIGHT = "right";
    private static final String ELEMENT_VALUE_CENTER = "center";
    private static final String COMA = ",";
    private static final String DOT = ".";
    private static final Byte SKIP_NO_CHART_VALUES = 12;
    private static final Byte SKIP_NO_CHART_DATES = 6;
    private static final Byte LENGTH_OF_WEEKDAY = 2;

    @Override
    public ProductChartDto getProductChart() {
        ProductChartDto productChartDto = new ProductChartDto();
        List<Double> values = new ArrayList<>();
        List<String> keys = new ArrayList<>();

        try {
            Document document = Jsoup.connect(CHART_URL).get();
            Elements prices = document.getElementsByAttributeValueContaining(ELEMENT_KEY_ALIGN, ELEMENT_VALUE_RIGHT);
            Elements dates = document.getElementsByAttributeValueContaining(ELEMENT_KEY_ALIGN, ELEMENT_VALUE_CENTER);

            prices.stream().skip(SKIP_NO_CHART_VALUES).forEach(e -> values.add(Double.valueOf(e.text().replace(COMA, DOT))));
            dates.stream()
                    .skip(SKIP_NO_CHART_DATES)
                    .filter(element -> element.text().length() > LENGTH_OF_WEEKDAY)
                    .forEach(e -> keys.add(e.text()));
            Map<String, Double> chart = IntStream.range(0, keys.size())
                    .boxed()
                    .collect(Collectors.toMap(keys::get, values::get));
            productChartDto.getValues().putAll(chart);
            return productChartDto;
        } catch (IOException e) {
            log.error("Parse html has an error by url: {}", CHART_URL);
            throw new RuntimeException(String.format("Jsoup connection failed for url: %s", CHART_URL));
        }
    }
}
