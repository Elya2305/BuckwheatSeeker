package com.progastination.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Shop {
    AUCHAN("Ашан", "auchan"),
    METRO("Метро", "metro"),
    EKO_MARKET("Экомаркет", "ekomarket");

    private final String name;
    private final String identifier;

    Shop(String name, String identifier) {
        this.name = name;
        this.identifier = identifier;
    }

    public static Shop getByIdentifier(String identifier) {
        return Arrays.stream(Shop.values())
                .filter(o -> o.getIdentifier().equals(identifier))
                .findFirst().orElse(null);
    }

    public static List<String> allIdentifiers() {
        return Arrays.stream(Shop.values())
                .map(Shop::getIdentifier)
                .collect(Collectors.toList());
    }
}
