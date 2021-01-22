package com.progastination.service;

import com.progastination.dto.ProductChartDto;

import java.io.IOException;
import java.util.List;

public interface ProductChartService {
    ProductChartDto getProductChart() throws IOException;
}
