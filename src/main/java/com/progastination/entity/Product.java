package com.progastination.entity;

import com.progastination.dto.ProducerDto;
import com.progastination.dto.ShopPriceDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// todo add unit
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "products")
@Data
@Entity
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    private String ean;
    private String categoryId;
    private String image;
    private String title;
    private String webUrl;
    private Integer weight;
    private String unit;
    @ManyToOne
    private Category category; // parentCategoryId

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ShopPriceDto> prices = new ArrayList<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ProducerDto producer;

    public ShopPriceDto getOrCreatePriceByShop(Shop shop) {
        ShopPriceDto shopPrice = prices.stream().filter(o -> shop.equals(o.getShop())).findFirst().orElse(new ShopPriceDto());
        shopPrice.setShop(shop);
        return shopPrice;
    }

    public void addPrice(ShopPriceDto price) {
        this.prices.add(price);
    }
}
