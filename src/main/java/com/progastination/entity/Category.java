package com.progastination.entity;

import com.progastination.utils.converter.ShopConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.catalina.Store;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Table(name = "categories")
@Data
@Entity
@EqualsAndHashCode(exclude = "category")
@ToString(exclude = {"category"})
public class Category {
    @Id
    private String identifier;

    private String title;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Category> subCategories;

    private Integer count;

    private String image;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = ShopConverter.class)
    private Set<Shop> shops = new HashSet<>();

    public void addShop(Shop shop) {
        this.shops.add(shop);
    }

    public enum ShopCategory {
        BAKERY("bakery"),
        FRUITS_AND_VEGETABLES("fruits-and-vegetables"),
        DAIRY_AMD_EGGS("dairy-and-eggs"),
        MEAT_FISH_POULTRY("meat-fish-poultry"),
        FROZEN("frozen"),
        DRINKS("drinks"),
        GROCERY("grocery"),
        PACKETS_CEREALS_METRO("packets-cereals") {
            @Override
            public List<Shop> availableStore() {
                return Collections.singletonList(Shop.METRO);
            }
        };


        @Getter
        private final String identifier;

        ShopCategory(String identifier) {
            this.identifier = identifier;
        }

        public static List<String> categoryIdentifiers() {
            return Arrays.stream(ShopCategory.values())
                    .map(ShopCategory::getIdentifier)
                    .collect(Collectors.toList());
        }

        public List<Shop> availableStore() {
            return Arrays.asList(Shop.values());
        }
    }
}
