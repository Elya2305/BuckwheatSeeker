package com.progastination.utils.converter;

import com.progastination.entity.Shop;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class ShopConverter implements AttributeConverter<Set<Shop>, String> {
    private static final String DELIMITER = " : ";

    @Override
    public String convertToDatabaseColumn(Set<Shop> shops) {
        return shops.stream().map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<Shop> convertToEntityAttribute(String s) {
        return Arrays.stream(s.split(DELIMITER))
                .map(Shop::valueOf).collect(Collectors.toSet());
    }
}
