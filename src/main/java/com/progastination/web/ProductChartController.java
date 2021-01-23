package com.progastination.web;

import com.progastination.dto.ProductChartDto;
import com.progastination.service.ProductChartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/chart")
public class ProductChartController {
    private final ProductChartService productChartService;

    @GetMapping("/buckwheat")
    public ProductChartDto getChart() {
        log.info("Request on getting buckwheat chart");
        return productChartService.getProductChart();
    }
}
