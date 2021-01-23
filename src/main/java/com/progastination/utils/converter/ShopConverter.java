package com.progastination.utils.converter;

import com.progastination.entity.Shop;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class ShopConverter implements AttributeConverter<Set<Shop>, String> {
    private static final String DELIMITER_INTERNAL = " : ";
    private static final String DELIMITER_DB = ",";

    @Override
    public String convertToDatabaseColumn(Set<Shop> shops) {
        return shops.stream().map(Enum::name)
                .collect(Collectors.joining(DELIMITER_INTERNAL));
    }

    @Override
    public Set<Shop> convertToEntityAttribute(String s) {
        return Arrays.stream(s.split(DELIMITER_INTERNAL))
                .map(Shop::valueOf).collect(Collectors.toSet());
    }

    public List<Shop> convertFromStringArray(String shops) {
        return Arrays.stream(shops.split(DELIMITER_DB)).map(this::convertToEntityAttribute)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }
}
