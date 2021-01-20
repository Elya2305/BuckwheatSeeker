package com.progastination.entity;

import com.progastination.utils.converter.ShopConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    private String ean;

//    private String categoryId;
//
//    private String image;
//
//    private Integer price;
//
//    private String title;
//
//    private String webUrl;
//
//    private Integer weight;
//
//    @Column(columnDefinition = "TEXT")
//    @Convert(converter = ShopConverter.class)
//    private Set<Shop> shops = new HashSet<>();
//
//    public void addShop(Shop shop) {
//        this.shops.add(shop);
//    }
}
