package com.progastination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductChartDto {
    private Map<String, Double> values = new TreeMap<>(this::compareDates);

    private int compareDates(String date1, String date2){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formatter.parse(date1).compareTo(formatter.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
